package br.com.jm.mspatrimonio.resource;

import java.util.List;
import java.util.logging.Logger;

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

import br.com.jm.mspatrimonio.dto.MarcaDTO;
import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.service.MarcaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Api(tags = {"Marca API"})
@SwaggerDefinition(tags = {
		@Tag(name = "Marca API", description = "API para Marcas")})	
@RestController
@RequestMapping("/marca")
public class MarcaResource {
	
	Logger logger = Logger.getLogger(MarcaResource.class.getName());
	
	@Autowired
	private MarcaService service;
	
	@Operation(summary = "Pesquisar Marca", description = "Pesquisar marca por nome", tags = { "Marca API" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Sucesso na operação", 
	                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Patrimonio.class)))) })		
	@GetMapping(path = "pesquisar")
	public ResponseEntity<List<Marca>> findByNome(
			@RequestParam("nome") String nome) {
		
		try {
			List<Marca> lista = service.findMarca(nome); 
			
			if(lista.isEmpty()) {
				logger.info(String.format("%s Não encontrado", nome));
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}
	
	@Operation(summary = "Incluir Marca", description = "Inserir uma nova Marca", tags = { "Marca API" })
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	@PutMapping
	public void salvar(@RequestBody MarcaDTO marca) {
		service.salvar(marca);
	}
	
	@Operation(summary = "Deletar Marca", description = "Deletar uma Marca", tags = { "Marca API" })
	@DeleteMapping(path = "/{id}")
	@ApiParam(name = "id", value= "Id da Marca a ser deletada", required = true)
	public void delete(@PathVariable("id") Long id) {
		service.deletar(id);
	}

}
