package br.com.universidade.gerenciamento.controller.dto;

import java.time.LocalDateTime;

import br.com.universidade.gerenciamento.model.Curso;

public class CursoResponseDto {

	private Long id;
	
	private String codigo;
	
	private String nome;
	
	private LocalDateTime dataCadastro;
	
	private Long cargaHoraria;

	public CursoResponseDto(Curso curso) {
		this.id = curso.getId();
		this.codigo = curso.getCodigo();
		this.nome = curso.getNome();
		this.dataCadastro = curso.getDataCadastro();
		this.cargaHoraria = curso.getCargaHoraria();
	}
	
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
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
