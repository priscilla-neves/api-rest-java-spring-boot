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

import br.com.universidade.gerenciamento.model.Curso;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CursoRepositoryTest {
	
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
		assertTrue(cursoRepository.findById(curso.getId()).isPresent());
	}

	@Test
	public void saveAndFindAll() {
		Curso curso = new Curso();
		curso.setNome("Curso 1");
		curso.setCargaHoraria(240L);
		curso.setDataCadastro(LocalDateTime.of(2020, Month.SEPTEMBER, 3, 13, 30));
		curso.setCodigo("MED2020");
		cursoRepository.save(curso);
		assertNotNull(cursoRepository.findAll());
	}

	@Test
	public void findByIdNotFound() {
		Curso curso = new Curso();
		curso.setId(-2L);
		assertFalse(cursoRepository.findById(curso.getId()).isPresent());
	}
}
