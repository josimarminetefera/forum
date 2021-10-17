package br.com.alura.forum_alura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	// localhost:8080
	@RequestMapping("/")
	@ResponseBody // para abrir na tela e não tentar encontrar uma pagina com o nome Ola Mundo
	public String hello() {
		return "Ola Mundo";
	}
}
