package br.com.universidade.gerenciamento.controller.dto;

import br.com.universidade.gerenciamento.model.Aluno;
import br.com.universidade.gerenciamento.model.Curso;

public class AlunoResponseDto {

	private Curso curso;
	
	private Long id;

	private String matricula;
	
	private String nome;
	
	private String cpf;
	
	private String endereco;
	
	private String cep;
	
	private String email;
	
	private String telefone;
	
	public AlunoResponseDto(Aluno aluno) {
		this.curso = aluno.getCurso();
		this.id = aluno.getId();
		this.matricula = aluno.getMatricula();
		this.nome = aluno.getNome();
		this.cpf = aluno.getCpf();
		this.endereco = aluno.getEndereco();
		this.cep = aluno.getCep();
		this.email = aluno.getEmail();
		this.telefone = aluno.getTelefone();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
