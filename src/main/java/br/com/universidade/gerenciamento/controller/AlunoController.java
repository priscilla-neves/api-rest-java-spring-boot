package br.com.universidade.gerenciamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.universidade.gerenciamento.controller.dto.AlunoDto;
import br.com.universidade.gerenciamento.controller.dto.AlunoResponseDto;
import br.com.universidade.gerenciamento.form.AlunoForm;
import br.com.universidade.gerenciamento.form.AtualizarAlunoForm;
import br.com.universidade.gerenciamento.model.Aluno;
import br.com.universidade.gerenciamento.repository.AlunoRepository;
import br.com.universidade.gerenciamento.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Grupo de endpoints para criar, listar, atualizar e deletar alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@Operation(
		summary = "Listar todos os alunos",
		description = "Retorna uma lista com todas os alunos cadastrados"
	)
	@GetMapping()
	public List<AlunoDto> findAll() {
		List<Aluno> alunos = alunoRepository.findAll();
		return AlunoDto.converter(alunos);
	}
	
	@Operation(summary = "Buscar aluno", description = "Buscar um aluno")
	@GetMapping(value = "/{id}")
	public ResponseEntity<AlunoDto> findById(@PathVariable(value = "id") Long id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if (aluno.isPresent())
			return ResponseEntity.ok(new AlunoDto(aluno.get()));
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(
		summary = "Adicionar um aluno",
		description = "Essa operacao salva um novo registro com as informacoes do aluno"
	)
	@PostMapping()
	@Transactional
	public ResponseEntity<AlunoResponseDto> save(@RequestBody @Valid AlunoForm form,
		UriComponentsBuilder uriBuilder) {
		Aluno aluno = form.converter(cursoRepository);
		alunoRepository.save(aluno);

		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoResponseDto(aluno));
	}

	@Operation(
		summary = "Atualizar aluno",
		description = "Essa operacao atualiza os dados de um aluno"
	)
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<AlunoResponseDto> update(@PathVariable Long id, @RequestBody @Valid AtualizarAlunoForm form) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno aluno = form.atualizar(id, alunoRepository);
			return ResponseEntity.ok(new AlunoResponseDto(aluno));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Operation(
		summary = "Excluir aluno",
		description = "Exclui o registro do aluno cadastrado"
	)
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		if (aluno.isPresent()) {
			alunoRepository.delete(aluno.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
