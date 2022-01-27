package br.com.alura.forum_alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableSpringDataWebSupport // isso aqui é para dar suporte a usar Pageable paginacao para não precisar
							// passar @RequestParam int pagina, @RequestParam int quantidade, @RequestParam
							// String ordenacao
@EnableCaching // Isso aqui é para habilitar o uso de cache na aplicação
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
