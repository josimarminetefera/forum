package br.com.alura.forum_alura.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum_alura.controller.form.LoginForm;

// Controller com a logica de autenticação
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	// @PostMapping pois vai receber parametros não por url e sim por body
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
		System.out.println(form.getEmail());
		System.out.println(form.getSenha());

		return ResponseEntity.ok().build();
	}
}
