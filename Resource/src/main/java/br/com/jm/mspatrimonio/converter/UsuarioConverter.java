package br.com.jm.mspatrimonio.converter;

import br.com.jm.mspatrimonio.dto.UsuarioDTO;
import br.com.jm.mspatrimonio.entity.Usuario;

public class UsuarioConverter {
	
	public static Usuario getUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSenha(usuarioDTO.getSenha());
		
		return usuario;
		
	}
	
	public static UsuarioDTO getUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setSenha(usuario.getSenha());
		
		return usuarioDTO;
		
	}
}
