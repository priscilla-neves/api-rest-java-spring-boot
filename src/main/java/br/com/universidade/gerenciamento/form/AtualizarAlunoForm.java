package br.com.universidade.gerenciamento.form;

import br.com.universidade.gerenciamento.model.Aluno;
import br.com.universidade.gerenciamento.repository.AlunoRepository;

public class AtualizarAlunoForm {

	private String nomeCurso;

	private String matricula;

	private String nome;
	
	private String cpf;

	private String endereco;

	private String cep;

	private String email;

	private String telefone;
	
	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
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

	public Aluno atualizar(Long id, AlunoRepository alunoRepository) {
		Aluno aluno = alunoRepository.getOne(id);
		aluno.setNome(this.nome);
		aluno.setCep(this.cep);
		aluno.setCpf(this.cpf);
		aluno.setEmail(this.email);
		aluno.setTelefone(this.telefone);
		aluno.setEndereco(this.endereco);
		aluno.setMatricula(this.matricula);
		return aluno;
	}
}
