package br.com.alura.forum_alura.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum_alura.modelo.Topico;
import br.com.alura.forum_alura.repository.TopicoRepository;

public class AtualiacaoTopicoForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;

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

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}

}
