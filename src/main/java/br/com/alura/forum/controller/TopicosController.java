package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalheTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualiacaoTopicoForm;
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
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm params,
			UriComponentsBuilder uriComponentsBuilder) {

		Topico topico = params.topicoFormParaTopico(cursoRepository);
		topicoRepository.save(topico);

		// Criar retorno de 201 caso de sucesso
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	// http://localhost:8080/topicos/12
	@GetMapping("/{id}") // isso aqui é uma url dinamica que recebe um parametro com o nome id
	public DetalheTopicoDto detalhar(@PathVariable Long id) {
		// @PathVariable indica que o parametro vem pela URI e não por ?
		Topico topico = topicoRepository.getOne(id);
		return new DetalheTopicoDto(topico);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualiacaoTopicoForm params) {
		Topico topico = params.atualizar(id, topicoRepository);
		return ResponseEntity.ok(new TopicoDto(topico));
	}
}
