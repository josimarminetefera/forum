package br.com.alura.forum_alura.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum_alura.modelo.Curso;
import br.com.alura.forum_alura.modelo.Topico;
import br.com.alura.forum_alura.repository.CursoRepository;

// Esta classe também é um DTO só que neste caso aqui os dados vem da 
// internet e as classes que tem dto mesmo é que sabem para net
// Aqui dentro tem todos campos que vão chegar do cliente
// Classe Pojo
public class TopicoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;

	@NotNull
	@NotEmpty
	private String nomeCurso;

	// Pode ser criado uma validação @Cpf por exemplo para criar uma nova formatação

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	// Método que converte o Dto TopicoForm para Topico
	public Topico topicoFormParaTopico(CursoRepository cursoRepository) {
		// buscar o curso para usar dentro do topico
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
