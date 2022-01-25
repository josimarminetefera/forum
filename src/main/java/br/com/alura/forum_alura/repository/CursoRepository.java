package br.com.alura.forum_alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum_alura.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
