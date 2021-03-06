package br.com.jm.mspatrimonio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jm.mspatrimonio.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	List<Marca> findByNomeContainingIgnoreCase(String nome);
	
	List<Marca> findByNome(String nome);
}


