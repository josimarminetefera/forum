package br.com.alura.forum_alura.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // Para habilitar o modulo de segurança na aplicação
@Configuration // Para indicar class de configuração
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

}
