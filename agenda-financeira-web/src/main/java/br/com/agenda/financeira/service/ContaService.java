package br.com.agenda.financeira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	public void salva(Conta conta) {
		repository.save(conta);
	}
	
	public void deleta(Conta conta) {
		repository.delete(conta);
	}
	
	public Conta buscaContaPorNumero(String numero) {
		return repository.findByNumero(numero);
	}
	
	public Conta buscaContaPorTitular(String titular) {
		return repository.findByTitular(titular);
	}
	
}
