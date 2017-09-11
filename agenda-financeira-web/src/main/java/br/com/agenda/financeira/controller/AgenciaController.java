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

import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.service.AgenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="agenda financeira")
@RestController
@RequestMapping("/agencias")
public class AgenciaController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AgenciaService service;

	@ApiOperation(value = "Lista de agencias", response = List.class)
	@GetMapping
	public List<Agencia> getAgencias(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "numero", required = false) String numero) throws Exception {
		
		log.info("Busca agencias com sucesso:..");
		return service.listaAgenciasPor(numero, nome);
	}
	
	@ApiOperation(value = "Grava agencia", response = List.class)
	@PostMapping
	public void salvar(@RequestBody Agencia agencia) {
		service.salva(agencia);
		log.info("Salvo agencia com sucesso:.." + agencia);
	}
	
	@ApiOperation(value = "Deleta agencia", response = List.class)
	@DeleteMapping
	public void delete(@RequestBody Agencia agencia) {
		service.deleta(service.buscaPorNumero(agencia.getNumero()));
		log.info("Deletado agencia com sucesso:.." + agencia);
	}
}
