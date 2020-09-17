package br.com.jm.mspatrimonio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.dto.MarcaDTO;
import br.com.jm.mspatrimonio.dto.PatrimonioDTO;
import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.entity.Patrimonio;
import br.com.jm.mspatrimonio.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<MarcaDTO> findMarca(String nome) {
		
		List<Marca> marcas = marcaRepository.findByNome(nome);
		
		List<MarcaDTO> asDto = marcas.stream().map(
		        s -> modelMapper.map(s, MarcaDTO.class)
		).collect(Collectors.toList());
	
		return asDto;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(MarcaDTO marcaDTO) {
		//converter
		Marca marca = new Marca();
		marca.setId(marcaDTO.getId());
		marca.setNome(marcaDTO.getNome());
		marcaRepository.saveAndFlush(marca);
	}
	
	public void deletar(Integer id) {
		marcaRepository.deleteById(id);
	}

	public String validar(MarcaDTO marcaDTO) {
		List <Marca>listaMarcaNome = marcaRepository.findByNome(marcaDTO.getNome());
		
		if (listaMarcaNome != null && listaMarcaNome.size()>0) {
			Marca marca = listaMarcaNome.get(0);
			
			if (!marca.getId().equals(marcaDTO.getId())) {
				return "Já existe outra Marca com o mesmo Nome, por favor escolha um Nome diferente";
			}
		}
		return null;
	}
	
	public String validarIncluir(MarcaDTO marcaDTO) {
		if ( marcaRepository.findById(marcaDTO.getId()).isPresent()) {
			return "Já existe outra Marca com o mesmo Id, por favor escolha um Id diferente";
		}
		
		List <Marca>listaMarcaNome = marcaRepository.findByNome(marcaDTO.getNome());
		
		if (listaMarcaNome != null && listaMarcaNome.size()>0) {
			Marca marca = listaMarcaNome.get(0);
			
			if (!marca.getId().equals(marcaDTO.getId())) {
				return "Já existe outra Marca com o mesmo Nome, por favor escolha um Nome diferente";
			}
		}
		return null;
	}

	public String validarDelete(Integer id) {
		if (!marcaRepository.findById(id).isPresent()) {
			return "Marca não existe";
		}
		return null;
	}
}
