package br.com.jm.mspatrimonio.resource;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jm.mspatrimonio.entity.Usuario;
import br.com.jm.mspatrimonio.service.UsuarioService;
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	Logger logger = Logger.getLogger(UsuarioResource.class.getName());
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping(path = "pesquisar")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Usuario>> findByNome(
			@RequestParam("nome") String nome) {
		
		try {
			List<Usuario> lista = service.findUsuario(nome); 
			
			if(lista.isEmpty()) {
				logger.info(String.format("%s Não encontrado", nome));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}
	
	@PutMapping
	public void salvarUsuario(@RequestBody Usuario usuario) {
		service.salvar(usuario);
	}

}
