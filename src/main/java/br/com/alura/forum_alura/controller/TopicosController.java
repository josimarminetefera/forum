package br.com.alura.forum_alura.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum_alura.controller.dto.DetalheTopicoDto;
import br.com.alura.forum_alura.controller.dto.TopicoDto;
import br.com.alura.forum_alura.controller.form.AtualiacaoTopicoForm;
import br.com.alura.forum_alura.controller.form.TopicoForm;
import br.com.alura.forum_alura.modelo.Topico;
import br.com.alura.forum_alura.repository.CursoRepository;
import br.com.alura.forum_alura.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos") // este controller responde a este nome /topicos
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;

	// http://localhost:8080/topicos
	// http://localhost:8080/topicos?pagina=1&quantidade=1
	// http://localhost:8080/topicos?pagina=0&quantidade=3&ordenacao=id
	// http://localhost:8080/topicos?nomeCurso=HTML%205
	// @RequestMapping(value = "/topicos", method = RequestMethod.GET)
	// parametro vem na URL e não é obrigatório @RequestParam(required = false)
	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina,
			@RequestParam int quantidade, @RequestParam String ordenacao) {
		// PODE SER USADO TAMBÉM UM (Pageable paginacao) no lugar da pagina,quantidade e
		// ordenação PARA ISSO DAR CERTO TEM QUE HABILITAR UM MODULO
		// para usar http://localhost:8080/topicos?page=0&size=3&sort=id,asc
		// @PageableDefault(sort="id")

		System.out.println(nomeCurso);

		Pageable paginacao = PageRequest.of(pagina, quantidade, Direction.DESC, ordenacao);

		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.topicoParaTopicoDto(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDto.topicoParaTopicoDto(topicos);
		}
	}

	// @RequestMapping(value = "/topicos", method = RequestMethod.POST)
	// parametro vem no corpo
	@PostMapping
	@Transactional
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
	public ResponseEntity detalhar(@PathVariable Long id) {
		// @PathVariable indica que o parametro vem pela URI e não por ?
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualiacaoTopicoForm params) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			Topico topicoAtualizado = params.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topicoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if (topico.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
