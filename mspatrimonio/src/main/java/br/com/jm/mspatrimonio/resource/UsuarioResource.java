package br.com.jm.mspatrimonio.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jm.mspatrimonio.dto.MensagemDTO;
import br.com.jm.mspatrimonio.dto.UsuarioDTO;
import br.com.jm.mspatrimonio.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Api(tags = {"Usuário API"})
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	Logger logger = Logger.getLogger(UsuarioResource.class.getName());
	
	@Autowired
	private UsuarioService service;
	
	@Operation(summary = "Pesquisar Usuário", description = "Pesquisar Usuário por nome", tags = { "Usuário API" })
	@GetMapping(path = "pesquisar")
	@Parameter(name = "nome",required = true, description = "Nome do Usuário")
	public ResponseEntity<List<UsuarioDTO>> findByNome(
			@RequestParam("nome") String nome) {
		
		try {
			List<UsuarioDTO> lista = service.findUsuario(nome); 
			
			if(lista.isEmpty()) {
				logger.info(String.format("%s Não encontrado", nome));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}
	
	@Operation(summary = "Incluir Usuário", description = "Inserir um novo Usuário", tags = { "Usuário API" })
	@PutMapping
	public ResponseEntity<MensagemDTO> salvarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		service.salvar(usuarioDTO);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MensagemDTO("Inserido com sucesso"));
	}
	
	@Operation(summary = "Deletar Usuário", description = "Deletar um Usuário pelo email", tags = { "Usuário API" })
	@DeleteMapping(path = "/{email}")
	@ApiParam(name = "email", value= "Email da Usuário a ser deletada", required = true)
	public void delete(@PathVariable("email") String email) {
		service.deletar(email);
	}	

}
