package br.com.jm.mspatrimonio.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UsuarioModel", description = "Usuário Model")
public class UsuarioDTO {
	
	@ApiModelProperty(value = "E-mail do Usuário", example = "joao.lins23829@gmail.com")
	@NotBlank(message = "email é obrigatório")
	private String email;
	
	@ApiModelProperty(value = "Nome do Usuário", example = "João Nogueira das Neves")
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	
	@ApiModelProperty(value = "Senha do Usuário", example = "si128#HK")
	@NotBlank(message = "senha é obrigatório")
	private String senha;
}
