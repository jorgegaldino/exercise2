package br.com.jm.mspatrimonio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MarcaModel", description = "Marca Model")
public class MarcaDTO {
	@NotNull(message = "Id é obrigatório")
	@ApiModelProperty(value = "Id da Marca", example = "35")
    private Integer id;

	@NotBlank(message = "nome é obrigatório")
	@ApiModelProperty(value = "Nome", example = "Sony")
    private String nome;
}
