package br.com.agenda.financeira.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.exception.NoContentException;
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
	
	public List<Conta> listaTodasContas() {
		return repository.findAll();
	}

	public List<Conta> listaContasPor(String numero, String titular) throws Exception {
		Set<Conta> contas = new HashSet<>();
		if (Objects.nonNull(titular)) {
			Conta conta = buscaContaPorTitular(titular);
			if (Objects.nonNull(conta)) {
				contas.add(buscaContaPorTitular(titular));
			}
		}

		if (Objects.nonNull(numero)) {
			Conta conta = buscaContaPorNumero(numero);
			if (Objects.nonNull(conta)) {
				contas.add(buscaContaPorNumero(numero));
			}
		}

		if (Objects.isNull(titular) && Objects.isNull(numero)) {
			contas.addAll(listaTodasContas());
		}
		
		if (contas.isEmpty()) {
			throw new NoContentException("Nao foi encontrado agencias na pesquisa");
		}
		return new ArrayList<>(contas);
	}
	
}
