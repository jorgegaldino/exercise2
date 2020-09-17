package br.com.jm.mspatrimonio.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	@NotBlank(message = "email é obrigatório")
	private String email;
	
	@NotBlank(message = "nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "senha é obrigatório")
	private String senha;
}
