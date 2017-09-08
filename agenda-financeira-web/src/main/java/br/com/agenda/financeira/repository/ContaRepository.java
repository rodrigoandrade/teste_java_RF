package br.com.agenda.financeira.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agenda.financeira.modelo.Conta;

public interface ContaRepository extends MongoRepository<Conta, String>  {

	public Conta findByNumero(String numero);
	
	public Conta findByTitular(String titular);
	
}
