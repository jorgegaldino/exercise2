package br.com.jm.mspatrimonio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.dto.UsuarioDTO;
import br.com.jm.mspatrimonio.entity.Usuario;
import br.com.jm.mspatrimonio.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<UsuarioDTO> findUsuario(String nome) {
		List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(nome);
		
		List<UsuarioDTO> asDto = usuarios.stream().map(
		        s -> modelMapper.map(s, UsuarioDTO.class)
		).collect(Collectors.toList());
	
		
		return asDto;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		usuarioRepository.saveAndFlush(usuario);
	}
	
	
	public String validar(UsuarioDTO usuario) {
		
		if (usuarioRepository.findById(usuario.getEmail()).isPresent()){
			return "Já existe esse um Usuário com esse Email no cadastro";
		}
		
		return "";
	}

	public void deletar(String email) {
		usuarioRepository.deleteById(email);
		
	}
}
