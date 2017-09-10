package br.com.agenda.financeira.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.exception.NoContentException;
import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repository;

	public void salva(String tipoOperacao, Transacao transacao) {
		OperacaoEnum operacao = OperacaoEnum.valueOf(tipoOperacao);
		transacao.setTaxa(operacao.calcular(transacao));
		repository.save(transacao);
	}
	
	public void deleta(Transacao transacao) {
		repository.delete(transacao);
	}
	
	public List<Transacao> buscaPorData(LocalDate data) throws Exception {
		return repository.findByData(data);
	}
	
	public List<Transacao> buscaPorAgendamento(LocalDate dataAgendamento) throws Exception {
		return repository.findByAgendamento(dataAgendamento);
	}
	
	public List<Transacao> buscaPorDataHeDataAgendamento(LocalDate data, LocalDate dataAgendamento) throws Exception {
		return repository.findByDataAndAgendamento(data, dataAgendamento);
	}

	public List<Transacao> listaTodasTransacoes() {
		return repository.findAll();
	}
	
	public List<Transacao> listaTransacoesPor(LocalDate data, LocalDate dataAgendamento) throws Exception {
		Set<Transacao> transacoes = new HashSet<>();
		if (Objects.nonNull(data)) {
			transacoes.addAll(buscaPorData(data));
		}

		if (Objects.nonNull(dataAgendamento)) {
			transacoes.addAll(buscaPorAgendamento(dataAgendamento));
		}

		if (Objects.isNull(data) && Objects.isNull(dataAgendamento)) {
			transacoes.addAll(listaTodasTransacoes());
		}
		
		if (transacoes.isEmpty()) {
			throw new NoContentException("Nao foi encontrado agencias na pesquisa");
		}
		return new ArrayList<>(transacoes);
	}
	
}
