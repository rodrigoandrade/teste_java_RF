package br.com.agenda.financeira.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.service.ContaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContaControllerTestIT {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private ContaService contaService;
	
	@LocalServerPort
	private int port;
	
	HttpHeaders headers = new HttpHeaders();

	private List<Conta> contas;
	
	@Before
	public void inicializa() {
		Agencia goias = new Agencia("31999", "1", "Goias Sao Caetano");
		Agencia domPedro = new Agencia("101011", "2", "Dom Pedro II");
		contas = new ArrayList<>();
		contas.add(new Conta("404", "2", "Maria", goias));
		contas.add(new Conta("319", "1", "Jose", domPedro));
		contas.add(new Conta("637", "2", "Manoel", goias));
		
		contas.forEach(contaService::salva);
	}
	
	@After
	public void finaliza() {
		contas.forEach(conta -> {
			Conta contaPorNumero = contaService.buscaContaPorNumero(conta.getNumero());
			if (Objects.nonNull(contaPorNumero)) {
				contaService.deleta(contaPorNumero);
			}
		});
	}
	
	@Test
    public void testGetTodasContas() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(criaURL("/contas"), HttpMethod.GET, entity, String.class);
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
    }
	
	@Test
	public void testGetContasPorNumero() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/contas?numero="+conta.getNumero()), HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}

	@Test
	public void testGetContaPorTitular() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/contas?titular="+conta.getTitular()), HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}

	@Test
	public void testGetContaPorTitularHeNumero() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/contas?titular="+conta.getTitular()+"&numero="+conta.getNumero()), 
					HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	@Test
	public void testGetContaSemConteudo() {
		Agencia agencia = new Agencia("4040", "8", "AGENCIA TESTE");
		Conta conta = new Conta("555555566", "9", "TESTE CONTA", agencia);
		HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
		ResponseEntity<String> response = restTemplate.exchange(
			criaURL("/contas?numero="+conta.getNumero()), HttpMethod.GET, entity, String.class);
			
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void testSalvar() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/contas"),
					HttpMethod.POST, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	@Test
	public void testDeletar() {
		contas.forEach(conta -> {
			HttpEntity<Conta> entity = new HttpEntity<Conta>(conta, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/contas"),
					HttpMethod.DELETE, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	private String criaURL(String uri) {
		return "http://localhost:" + port + uri;
	}

}
