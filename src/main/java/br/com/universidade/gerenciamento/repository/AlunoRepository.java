package br.com.universidade.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.universidade.gerenciamento.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
