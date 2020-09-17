package br.com.jm.mspatrimonio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.dto.PatrimonioDTO;
import br.com.jm.mspatrimonio.dto.UsuarioDTO;
import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.entity.Usuario;
import br.com.jm.mspatrimonio.repository.MarcaRepository;
import br.com.jm.mspatrimonio.repository.PatrimonioRepository;

@Service
@Transactional
public class PatrimonioService {
	
	@Autowired
	private PatrimonioRepository patrimonioRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PatrimonioDTO> findPatrimonio(String nome) {
		List<Patrimonio> patrimonios = patrimonioRepository.findByNome(nome);
		
		List<PatrimonioDTO> asDto = patrimonios.stream().map(
		        s -> modelMapper.map(s, PatrimonioDTO.class)
		).collect(Collectors.toList());
	
		return asDto;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer salvar(PatrimonioDTO patrimonioDTO) {
		Patrimonio patrimonio = modelMapper.map(patrimonioDTO, Patrimonio.class);
		patrimonioRepository.saveAndFlush(patrimonio);
		return patrimonio.getNumTombo();
	}
	
	public void deletar(Integer numTombo) {
		patrimonioRepository.deleteById(numTombo);
	}

	public String validar(PatrimonioDTO patrimonioDTO) {
		if (!marcaRepository.findById(patrimonioDTO.getMarcaId()).isPresent()) {
			return "MarcaId informado não existe";
		}
		return null;
	}

	public String validarDelete(Integer numTombo) {
		if (!patrimonioRepository.findById(numTombo).isPresent()) {
			return "Número de Tombo informado não existe";
		}
		return null;
	}
}
