package br.com.jm.mspatrimonio.resource;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.service.PatrimonioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.*;


@RestController(value = "Patrimonio")
@RequestMapping("/patrimonio")
public class PatrimonioResource {
	
	Logger logger = Logger.getLogger(PatrimonioResource.class.getName());
	
	@Autowired
	private PatrimonioService service;
	
	@Operation(summary = "Pesquisar Patrimonio", description = "Pesquisar patrimonio por nome", tags = { "patrimonio" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Sucesso na operação", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Patrimonio.class)))) })		
	@GetMapping(path = "pesquisar")
	public ResponseEntity<List<Patrimonio>> findByNome(
			@RequestParam("nome") String nome) {
		
		try {
			List<Patrimonio> lista = service.findPatrimonio(nome); 
			
			if(lista.isEmpty()) {
				logger.info(String.format("%s Não encontrado", nome));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}
	
	@Operation(summary = "Incluir Patrimonio", description = "Inserir um novo patrimonio", tags = { "patrimonio" })
	@PutMapping
	public void salvar(@RequestBody Patrimonio patrimonio) {
		service.salvar(patrimonio);
	}

}
