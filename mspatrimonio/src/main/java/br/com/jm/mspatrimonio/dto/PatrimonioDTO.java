package br.com.jm.mspatrimonio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "PatrimonioModel", description = "Patrimônio Model")
@Data
public class PatrimonioDTO {
	
	@ApiModelProperty(value = "Nome do Patrimônio", example = "Notebook")
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	
	@ApiModelProperty(value = "Descrição do Patrimônio", example = "Modelo T4 2012 - Intel9")
	@NotBlank(message = "descricao é obrigatório")
	private String descricao;
	
	@ApiModelProperty(value = "Id da Marca do Patrimônio", example = "34")
	@NotNull(message = "marcaId é obrigatório")
	private Integer marcaId;
}
