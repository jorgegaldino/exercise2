package br.com.jm.mspatrimonio.dto;

import lombok.Data;

@Data
public class MensagemDTO {
	private String mensagem;
	
	public MensagemDTO(String mensagem) {
		this.mensagem = mensagem;
	}
}
