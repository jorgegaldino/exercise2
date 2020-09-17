package br.com.jm.mspatrimonio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.repository.MarcaRepository;
import br.com.jm.mspatrimonio.repository.PatrimonioRepository;

@Service
@Transactional
public class PatrimonioService {
	
	@Autowired
	private PatrimonioRepository patrimonioRepository;
	
	public List<Patrimonio> findPatrimonio(String nome) {
		return patrimonioRepository.findByNome(nome);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(Patrimonio patrimonio) {
		patrimonioRepository.saveAndFlush(patrimonio);
	}
	
	public void deletar(Patrimonio patrimonio) {
		patrimonioRepository.delete(patrimonio);
	}
}
