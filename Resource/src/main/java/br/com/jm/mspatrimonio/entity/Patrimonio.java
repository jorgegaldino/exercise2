package br.com.jm.mspatrimonio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="patrimonio", schema = "ow_microservice")
@Data
public class Patrimonio {
	
    @Id
	@Column(name="num_tombo")
	private Long numTombo;
	
    @Column(name="nome")
	private String nome;
	
    @Column(name="descricao")
	private String descricao;
	
    @Column(name="marca_id")
	private Integer marcaId;
}
