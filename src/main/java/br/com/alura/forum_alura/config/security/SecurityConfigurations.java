package br.com.alura.forum_alura.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // Para habilitar o modulo de segurança na aplicação
@Configuration // Para indicar class de configuração
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	// Configuração de autenticação login acesso
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}

	// Configuração de autorizações perfis para discubrir o que tem acesso, quais
	// url são publicas e quais privadas
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.GET, "/topicos/*").permitAll().anyRequest().authenticated().and().formLogin();
	}

	// Configuração de recursos estáticos permissão para arquivos js css imagens
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

}
