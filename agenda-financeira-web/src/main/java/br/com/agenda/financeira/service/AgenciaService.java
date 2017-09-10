package br.com.agenda.financeira.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.financeira.exception.NoContentException;
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
	
	public Agencia buscaPorNumero(String numero) {
		return repository.findByNumero(numero);
	}

	public Agencia buscaPorNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public List<Agencia> listaTodasAgencias() {
		return repository.findAll();
	}
	
	public List<Agencia> listaAgenciasPor(String numero, String nome) throws Exception {
		Set<Agencia> agencias = new HashSet<>();
		if (Objects.nonNull(nome)) {
			Agencia agencia = buscaPorNome(nome);
			if (Objects.nonNull(agencia)) {
				agencias.add(buscaPorNome(nome));
			}
		}

		if (Objects.nonNull(numero)) {
			Agencia agencia = buscaPorNumero(numero);
			if (Objects.nonNull(agencia)) {
				agencias.add(buscaPorNumero(numero));
			}
		}

		if (Objects.isNull(nome) && Objects.isNull(numero)) {
			agencias.addAll(listaTodasAgencias());
		}
		
		if (agencias.isEmpty()) {
			throw new NoContentException("Nao foi encontrado agencias na pesquisa");
		}
		return new ArrayList<>(agencias);
	}
}
