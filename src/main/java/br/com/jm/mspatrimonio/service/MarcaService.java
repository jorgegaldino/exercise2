package br.com.jm.mspatrimonio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jm.mspatrimonio.entity.Marca;
import br.com.jm.mspatrimonio.entity.Usuario;
import br.com.jm.mspatrimonio.repository.MarcaRepository;
import br.com.jm.mspatrimonio.repository.UsuarioRepository;

@Service
@Transactional
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public List<Usuario> findUsuario(String nome) {
		return marcaRepository.findByNome(nome);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(Marca marca) {
		marcaRepository.saveAndFlush(marca);
	}
	
	public void deletar(Marca marca) {
		marcaRepository.delete(marca);
	}
}
