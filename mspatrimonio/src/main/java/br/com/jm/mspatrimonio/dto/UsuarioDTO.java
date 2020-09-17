package br.com.jm.mspatrimonio.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UsuarioModel", description = "Usuário Model")
public class UsuarioDTO {
	
	@ApiModelProperty(value = "E-mail do Usuário", example = "joao.lins23829@gmail.com")
	@NotBlank(message = "email é obrigatório")
	@Email(message = "Informe um email inválido")
	private String email;
	
	@ApiModelProperty(value = "Nome do Usuário", example = "João Nogueira das Neves")
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	
	@ApiModelProperty(value = "Senha do Usuário", example = "si128#HK")
	@NotBlank(message = "senha é obrigatório")
	@Pattern(message = "Senha deve ter 8 caracteres ou mais, com pelo menos uma letra maiúscula, uma minúscula, um número e um caracter especial", 
		regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$" )
	private String senha;
}
