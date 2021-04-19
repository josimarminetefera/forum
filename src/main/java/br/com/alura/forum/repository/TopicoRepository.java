package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// este aqui é o parametro nome dentro da entidade Curso
	List<Topico> findByCurso_Nome(String nomeCurso);

	// este aqui é o parametro String nomeCurso
	// List<Topico> findByCursoNome(String nomeCurso);

	@Query("select t from Topico t where t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);

}
