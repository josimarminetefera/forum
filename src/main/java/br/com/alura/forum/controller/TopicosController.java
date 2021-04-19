package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos") // este controller responde a este nome /topicos
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	// http://localhost:8080/topicos
	// http://localhost:8080/topicos?nomeCurso=HTML%205
	// @RequestMapping(value = "/topicos", method = RequestMethod.GET)
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		System.out.println(nomeCurso);
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.topicoParaTopicoDto(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			return TopicoDto.topicoParaTopicoDto(topicos);
		}
	}

	// @RequestMapping(value = "/topicos", method = RequestMethod.POST)
	@PostMapping
	public void cadastrar(@RequestBody TopicoForm topicoForm) {
		Topico topico = topicoForm.topicoFormParaTopico(cursoRepository);
		topicoRepository.save(topico);
	}
}
