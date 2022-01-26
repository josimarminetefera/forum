package br.com.alura.forum_alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableSpringDataWebSupport // isso aqui é para dar suporte a usar Pageable paginacao para não precisar
							// passar @RequestParam int pagina, @RequestParam int quantidade, @RequestParam
							// String ordenacao
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
