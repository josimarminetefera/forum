package br.com.alura.forum_alura.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // Para habilitar o modulo de segurança na aplicação
@Configuration // Para indicar class de configuração
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	// Configuração de autenticação login acesso
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// userDetailsService serve para indicar qual será a classe de autenticação
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuração de autorizações perfis para discubrir o que tem acesso, quais
	// url são publicas e quais privadas
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll()
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.anyRequest().authenticated() // todos outas url precisa autenticar
			.and().csrf().disable() // csrf é para evitar ataques hacker
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //politica de criação de sessao inativada
			// Essa parte foi removida pois serve só para aplicações web que vão salvar em sessão
			// .and().formLogin(); // sem autenticacao abrwe o formulario
	}

	// Configuração de recursos estáticos permissão para arquivos js css imagens
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}
