package br.com.alura.forum_alura.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum_alura.modelo.Topico;

//INTERFACE NÃO PRECISA DE TER NENHUM ANOTAÇÃO
public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// este aqui é o parametro nome dentro da entidade Curso
	// Curso é entidade Nome é atributo da classe Curso
	// Para corrijir ambiguidade tem o _ é para avisar que é para pegar dentro da
	// Classe Curso Curso_Nome
	Page<Topico> findByCurso_Nome(String nomeCurso, Pageable paginacao);

	// este aqui é o parametro String nomeCurso
	// List<Topico> findByCursoNome(String nomeCurso);
	// voce pode querer mudar o nome ai voce tem a possibilidade de @Query
	@Query("select t from Topico t where t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);

}
