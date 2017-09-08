package br.com.agenda.financeira.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.agenda.financeira.modelo.Transacao;

public interface TransacaoRepository extends MongoRepository<Transacao, String> {

	public List<Transacao> findByData(LocalDate data);
	
	public List<Transacao> findByAgendamento(LocalDate dataAgendamento);

	public List<Transacao> findByDataAndAgendamento(LocalDate data, LocalDate dataAgendamento);
	
}
