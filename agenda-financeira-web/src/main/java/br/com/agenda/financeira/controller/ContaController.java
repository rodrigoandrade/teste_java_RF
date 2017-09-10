package br.com.agenda.financeira.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ContaService service;

	@GetMapping
	public List<Conta> getContas(@RequestParam(value = "titular", required = false) String titular,
			@RequestParam(value = "numero", required = false) String numero) throws Exception {
		
		log.info("Busca contas com sucesso:..");
		return service.listaContasPor(numero, titular);
	}
	
	@PostMapping
	public void salvar(@RequestBody Conta conta) {
		service.salva(conta);
		log.info("Salvo conta com sucesso:.." + conta);
	}
	
	@DeleteMapping
	public void delete(@RequestBody Conta conta) {
		service.deleta(service.buscaContaPorNumero(conta.getNumero()));
		log.info("Deletado conta com sucesso:.." + conta);
	}

}
