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

import br.com.universidade.gerenciamento.controller.dto.CursoCreateDto;
import br.com.universidade.gerenciamento.controller.dto.CursoDto;
import br.com.universidade.gerenciamento.controller.dto.CursoResponseDto;
import br.com.universidade.gerenciamento.form.AtualizarCursoForm;
import br.com.universidade.gerenciamento.model.Curso;
import br.com.universidade.gerenciamento.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Grupo de endpoints para criar, listar, atualizar e deletar cursos")
public class CursoController {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Operation(
		summary = "Listar todos os cursos",
		description = "Retorna uma lista com todas os cursos cadastrados"
	)
	@GetMapping()
	public List<CursoDto> findAll() {
		List<Curso> cursos = cursoRepository.findAll();
		return CursoDto.converter(cursos);
	}

	@Operation(summary = "Buscar curso", description = "Buscar um curso")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CursoDto> findById(@PathVariable(value = "id") Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent())
			return ResponseEntity.ok(new CursoDto(curso.get()));
		else
			return ResponseEntity.notFound().build();
	}

	@Operation(
		summary = "Adicionar um curso",
		description = "Essa operacao salva um novo registro com as informacoes do curso"
	)
	@PostMapping()
	@Transactional
	public ResponseEntity<CursoResponseDto> save(@RequestBody CursoCreateDto cursoDTO,
		UriComponentsBuilder uriBuilder) {
		Curso curso = cursoRepository.save(cursoDTO.transformToNewCourse());
		URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(new CursoResponseDto(curso));
	}

	@Operation(
		summary = "Atualizar curso",
		description = "Essa operacao atualiza os dados de um curso"
	)
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<CursoResponseDto> update(@PathVariable Long id, @RequestBody @Valid AtualizarCursoForm form) {
		Optional<Curso> optional = cursoRepository.findById(id);
		if (optional.isPresent()) {
			Curso curso = form.atualizar(id, cursoRepository);
			return ResponseEntity.ok(new CursoResponseDto(curso));
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(
		summary = "Excluir curso",
		description = "Exclui o registro do curso cadastrado"
	)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			cursoRepository.delete(curso.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
