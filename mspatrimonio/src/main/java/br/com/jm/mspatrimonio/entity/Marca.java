package br.com.jm.mspatrimonio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="marca", schema = "ow_microservice")
@Data
public class Marca {
	
    @Id
	@Column(name="id")
    private Integer id;
    
    @Column(name="nome")
    private String nome;
}
