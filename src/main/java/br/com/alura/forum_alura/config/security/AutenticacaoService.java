package br.com.alura.forum_alura.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum_alura.controller.dto.DetalheTopicoDto;
import br.com.alura.forum_alura.modelo.Topico;
import br.com.alura.forum_alura.modelo.Usuario;
import br.com.alura.forum_alura.repository.UsuarioRepository;

// UserDetailsService para indicar para o spring que é a service de autenticação
@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// Quando clicado no loguin da tela de login ele chama este método
	// loadUserByUsername
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username é o email informado que vai buscar no banco de dados
		// a senha ele não pega pois é o spring que vai buscar em memória se a senha
		// está certa ou não
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

}
