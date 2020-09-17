package br.com.jm.mspatrimonio.entity;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="usuario", schema = "ow_microservice")
@Data
public class Usuario {
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="senha")
	private String senha;
	
	
}
