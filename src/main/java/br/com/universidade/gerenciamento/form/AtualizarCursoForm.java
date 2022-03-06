package br.com.universidade.gerenciamento.form;

import java.time.LocalDateTime;

import br.com.universidade.gerenciamento.model.Curso;
import br.com.universidade.gerenciamento.repository.CursoRepository;

public class AtualizarCursoForm {

	private Long id;
	
	private String codigo;
	
	private String nome;
	
	private Long cargaHoraria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public Curso atualizar(Long id, CursoRepository cursoRepository) {
		Curso curso = cursoRepository.getOne(id);
		curso.setCodigo(this.codigo);
		curso.setNome(this.nome);
		curso.setDataCadastro(LocalDateTime.now());
		curso.setCargaHoraria(this.cargaHoraria);
		return curso;
	}
}
