package br.com.jm.mspatrimonio.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jm.mspatrimonio.entity.Patrimonio;


@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {
	List<Patrimonio> findByNomeContainingIgnoreCase(String nome);
}


