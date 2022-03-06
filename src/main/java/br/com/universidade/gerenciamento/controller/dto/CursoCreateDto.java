package br.com.universidade.gerenciamento.controller.dto;

import java.time.LocalDateTime;

import br.com.universidade.gerenciamento.model.Curso;

public class CursoCreateDto {

	private String codigo;

	private String nome;

	private Long cargaHoraria;
	
	public CursoCreateDto() {
		super();
	}
	
	public CursoCreateDto(Curso curso) {
		this.codigo = curso.getCodigo();
		this.nome = curso.getNome();
		this.cargaHoraria = curso.getCargaHoraria();
	}

	public Curso transformToNewCourse() {
		return new Curso(
			null,
			codigo,
			nome,
			LocalDateTime.now(),
			cargaHoraria
		);
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
}
