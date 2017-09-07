package br.com.agenda.financeira.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agenda.financeira.modelo.Agencia;

public interface AgenciaRepository extends MongoRepository<Agencia, String> {

	public Agencia findByNumero(String numero);

	public Agencia findByNome(String nome);
	
}
