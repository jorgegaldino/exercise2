package br.com.jm.mspatrimonio.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.converter.UsuarioConverter;
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
	
	public List<Usuario> findUsuario(String nome) {
		List<Usuario> usuarios = usuarioRepository.findByNome(nome);
		
	
		
		return usuarioRepository.findByNome(nome);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		usuarioRepository.saveAndFlush(usuario);
	}
	
	public void deletar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public String validar(UsuarioDTO usuario) {
		
		if (usuario.getEmail() != null) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(usuario.getEmail());
            if (!matcher.matches()) {
            	return "Email com formato inválido";
            }
			
		}
		if (usuarioRepository.findById(usuario.getEmail()).isPresent()){
			return "Já existe esse um Usuário com esse Email no cadastro";
		}
		if (usuarioRepository.findByNome(usuario.getNome()).size() >0){
			return "Já existe esse um Usuário com esse Nome no cadastro";
		}
		
		if (usuario.getSenha() != null) {
			String regex = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(usuario.getSenha());
            if (!matcher.matches()) {
            	return "Senha deve ter 8 caracteres ou mais, com pelo menos uma letra maiúscula, uma minúscula, um número e um caracter especial";
            }
		}
		
		
		return "";
	}
}
