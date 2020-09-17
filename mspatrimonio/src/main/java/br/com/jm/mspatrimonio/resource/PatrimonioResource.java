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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jm.mspatrimonio.dto.MensagemDTO;
import br.com.jm.mspatrimonio.dto.PatrimonioDTO;
import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.service.PatrimonioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.*;

@Api(tags = {"Patrimônio API"})
@RestController(value = "Patrimonio")
@RequestMapping("/patrimonio")
public class PatrimonioResource {
	
	Logger logger = Logger.getLogger(PatrimonioResource.class.getName());
	
	@Autowired
	private PatrimonioService service;
	
	
	
	@Operation(summary = "Pesquisar Patrimônio", description = "Pesquisar Patrimônio por nome", tags = { "Patrimônio API" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Sucesso na operação", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Patrimonio.class)))) })		
	@GetMapping(path = "pesquisar")
	public ResponseEntity<List<PatrimonioDTO>> findByNome(
			@RequestParam("nome") String nome) {
		
		try {
			List<PatrimonioDTO> lista = service.findPatrimonio(nome); 
			
			if(lista.isEmpty()) {
				logger.info(String.format("%s Não encontrado", nome));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}
	
	@Operation(summary = "Alterar Patrimônio", description = "Alterar um patrimonio", tags = { "Patrimônio API" })
	@PutMapping
	@ApiParam(name = "numTombo", value= "Número do Tombo do Patrimônio a ser alterado", required = true)
	public ResponseEntity<Object>  salvar(@Valid @RequestBody PatrimonioDTO patrimonioDTO, @PathVariable("numTombo") Integer numTombo) {
		String mensagemValidacao = service.validar(patrimonioDTO);
		
		if (mensagemValidacao != null && !mensagemValidacao.equals("")) {
			return ResponseEntity.unprocessableEntity().body(new MensagemDTO(mensagemValidacao));
		}
		
		return ResponseEntity.ok(service.salvar(patrimonioDTO));
	}
	
	@Operation(summary = "Incluir Patrimônio", description = "Inserir um novo Patrimônio", tags = { "Patrimônio API" })
	@PostMapping
	public ResponseEntity<Object>  create(@Valid @RequestBody PatrimonioDTO patrimonioDTO) {
		String mensagemValidacao = service.validar(patrimonioDTO);
		
		if (mensagemValidacao != null && !mensagemValidacao.equals("")) {
			return ResponseEntity.unprocessableEntity().body(new MensagemDTO(mensagemValidacao));
		}
		
		return ResponseEntity.ok(new MensagemDTO(String.format("Tombo de Número %d inserido com sucesso", service.salvar(patrimonioDTO))));
	}
	
	@Operation(summary = "Deletar Patrimônio", description = "Deletar um Patrimônio pelo Número de tombo", tags = { "Patrimônio API" })
	@DeleteMapping(path = "/{numTombo}")
	@ApiParam(name = "numTombo", value= "Número do Tombo do Patrimônio a ser deletado", required = true)
	public ResponseEntity<Object> delete(@PathVariable("numTombo") Integer numTombo) {
		String mensagemValidacao = service.validarDelete(numTombo);
		
		if (mensagemValidacao != null && !mensagemValidacao.equals("")) {
			return ResponseEntity.unprocessableEntity().body(new MensagemDTO(mensagemValidacao));
		}
		
		service.deletar(numTombo); 
		
		return ResponseEntity.ok().build();
	}	

}
