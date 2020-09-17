package br.com.jm.mspatrimonio.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MarcaModel", description = "Marca Model")
public class MarcaDTO {

	@ApiModelProperty(value = "Id da Marca", example = "35")
    private Long id;

	@ApiModelProperty(value = "Nome", example = "Sony")
    private String nome;
}
