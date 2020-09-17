package br.com.jm.mspatrimonio.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MensagemModel", description = "Mensagem Model")
@Data
public class MensagemDTO {
	
	@ApiModelProperty(value = "Mensagem de Retorno", example = "Nome já existe")
	private String mensagem;
	
	public MensagemDTO(String mensagem) {
		this.mensagem = mensagem;
	}
}
