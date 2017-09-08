package br.com.agenda.financeira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.repository.AgenciaRepository;

@Service
public class AgenciaService {

	@Autowired
	private AgenciaRepository repository;
	
	public void salva(Agencia agencia) {
		repository.save(agencia);
	}
	
	public void deleta(Agencia agencia) {
		repository.delete(agencia);
	}
	
	public Agencia findByNumero(String numero) {
		return repository.findByNumero(numero);
	}

	public Agencia findByNome(String nome) {
		return repository.findByNome(nome);
	}
}
