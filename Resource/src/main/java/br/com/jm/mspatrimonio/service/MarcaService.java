package br.com.jm.mspatrimonio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.dto.MarcaDTO;
import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public List<Marca> findMarca(String nome) {
		return marcaRepository.findByNome(nome);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(MarcaDTO marcaDTO) {
		//converter
		Marca marca = new Marca();
		marca.setId(marcaDTO.getId());
		marca.setNome(marcaDTO.getNome());
		marcaRepository.saveAndFlush(marca);
	}
	
	public void deletar(Long id) {
		marcaRepository.deleteById(id);
	}
}
