package br.com.agenda.financeira.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TransacaoService service;

	@GetMapping
	public List<Transacao> getTransacoes(
			@RequestParam(value = "data", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
			@RequestParam(value = "dataAgendamento", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAgendamento)
			throws Exception {

		log.info("Busca agendamentos com sucesso:..");
		return service.listaTransacoesPor(data, dataAgendamento);
	}
	
	@PostMapping
	public void salvar(@RequestParam("tipoOperacao") String tipoOperacao,  @RequestBody Transacao transacao) {
		service.salva(tipoOperacao, transacao);
		log.info("Salvo conta com sucesso:.." + transacao);
	}
	
}
