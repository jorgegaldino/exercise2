package br.com.jm.mspatrimonio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.entity.Usuario;
import br.com.jm.mspatrimonio.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findUsuario(String nome) {
		return usuarioRepository.findByNome(nome);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(Usuario usuario) {
		/**@todovalidarEmail*/
		
		usuarioRepository.saveAndFlush(usuario);
	}
	
	public void deletar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}
