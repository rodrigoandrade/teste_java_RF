package br.com.agenda.financeira.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repository;

	public void salva(Transacao transacao, OperacaoEnum operacao) {
		transacao.setTaxa(operacao.calcular(transacao));
		repository.save(transacao);
	}
	
	public void deleta(Transacao transacao) {
		repository.delete(transacao);
	}
	
	public List<Transacao> buscaPorData(LocalDate data) {
		return repository.findByData(data);
	}
	
	public List<Transacao> buscaPorAgendamento(LocalDate dataAgendamento) {
		return repository.findByAgendamento(dataAgendamento);
	}

	public List<Transacao> buscaPorDataHeDataAgendamento(LocalDate data, LocalDate dataAgendamento) {
		return repository.findByDataAndAgendamento(data, dataAgendamento);
	}
	
}
