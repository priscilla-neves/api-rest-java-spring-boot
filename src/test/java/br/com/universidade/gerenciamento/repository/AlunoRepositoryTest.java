package br.com.universidade.gerenciamento.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import br.com.universidade.gerenciamento.model.Aluno;
import br.com.universidade.gerenciamento.model.Curso;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AlunoRepositoryTest {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	CursoRepository cursoRepository;

	@Test
	public void save() {
		Curso curso = new Curso();
		curso.setNome("Curso 1");
		curso.setCargaHoraria(240L);
		curso.setDataCadastro(LocalDateTime.of(2020, Month.SEPTEMBER, 3, 13, 30));
		curso.setCodigo("MED2020");
		cursoRepository.save(curso);
		Aluno aluno = new Aluno();
		aluno.setMatricula("1");
		aluno.setCurso(curso);
		aluno.setNome("Aluno 1");
		aluno.setCpf("14386715045");
		aluno.setCep("65000-000");
		aluno.setEmail("aluno1@gmail.com");
		aluno.setEndereco("Rua A, Quadra B");
		aluno.setTelefone("(98)98888-8888");
		alunoRepository.save(aluno);
		assertTrue(alunoRepository.findById(aluno.getId()).isPresent());
	}

	@Test
	public void saveAndFindAll() {
		Curso curso = new Curso();
		curso.setNome("Curso 1");
		curso.setCargaHoraria(240L);
		curso.setDataCadastro(LocalDateTime.of(2020, Month.SEPTEMBER, 3, 13, 30));
		curso.setCodigo("MED2020");
		cursoRepository.save(curso);

		Aluno aluno = new Aluno();
		aluno.setMatricula("1");
		aluno.setCurso(curso);
		aluno.setNome("Aluno 1");
		aluno.setCpf("14386715045");
		aluno.setCep("65000-000");
		aluno.setEmail("aluno1@gmail.com");
		aluno.setEndereco("Rua A, Quadra B");
		aluno.setTelefone("(98)98888-8888");
		alunoRepository.save(aluno);
		assertNotNull(alunoRepository.findAll());
	}

	@Test
	public void findByIdNotFound() {
		Aluno aluno = new Aluno();
		aluno.setId(-2L);
		assertFalse(alunoRepository.findById(aluno.getId()).isPresent());
	}
}
