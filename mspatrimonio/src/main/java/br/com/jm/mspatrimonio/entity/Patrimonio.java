package br.com.jm.mspatrimonio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="patrimonio", schema = "ow_microservice")
@Data
public class Patrimonio {
	
    @Id
	@Column(name="num_tombo")
    @SequenceGenerator(name="ow_microservice.patrimonio_num_tombo_seq",
    		sequenceName="ow_microservice.patrimonio_num_tombo_seq",
    			allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    		generator="ow_microservice.patrimonio_num_tombo_seq")
	private Integer numTombo;
	
    @Column(name="nome")
	private String nome;
	
    @Column(name="descricao")
	private String descricao;
	
    @Column(name="marca_id")
	private Integer marcaId;
}
