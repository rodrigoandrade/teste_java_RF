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
import br.com.agenda.financeira.service.AgenciaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AgenciaControllerTestIT {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private AgenciaService agenciaService;
	
	@LocalServerPort
	private int port;
	
	HttpHeaders headers = new HttpHeaders();

	private List<Agencia> agencias;
	
	@Before
	public void inicializa() {
		agencias = new ArrayList<>();
		agencias.add(new Agencia("101010", "2", "Figueiras Santo Andre"));
		agencias.add(new Agencia("31999", "1", "Goias Sao Caetano"));
		agencias.add(new Agencia("101011", "2", "Dom Pedro II"));
		
		agencias.forEach(agenciaService::salva);
	}
	
	@After
	public void finaliza() {
		agencias.forEach(agencia -> {
			Agencia agenciaPorNumero = agenciaService.findByNumero(agencia.getNumero());
			if (Objects.nonNull(agenciaPorNumero)) {
				agenciaService.deleta(agenciaPorNumero);
			}
		});
	}
	
	@Test
    public void testGetTodasAgencias() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(criaURL("/agencias"), HttpMethod.GET, entity, String.class);
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
    }
	
	@Test
	public void testGetAgenciaPorNumero() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/agencias?numero="+agencia.getNumero()), HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}

	@Test
	public void testGetAgenciaPorNome() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/agencias?nome="+agencia.getNome()), HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}

	@Test
	public void testGetAgenciaPorNomeHeNumero() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/agencias?nome="+agencia.getNome()+"&numero="+agencia.getNumero()), 
					HttpMethod.GET, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	@Test
	public void testGetAgenciaSemConteudo() {
		Agencia agencia = new Agencia("2827272727", "9", "TESTE");
		HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
		ResponseEntity<String> response = restTemplate.exchange(
			criaURL("/agencias?numero="+agencia.getNumero()), HttpMethod.GET, entity, String.class);
			
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void testSalvar() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/agencias"),
					HttpMethod.POST, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	@Test
	public void testDeletar() {
		agencias.forEach(agencia -> {
			HttpEntity<Agencia> entity = new HttpEntity<Agencia>(agencia, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					criaURL("/agencias"),
					HttpMethod.DELETE, entity, String.class);
			
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
	}
	
	private String criaURL(String uri) {
		return "http://localhost:" + port + uri;
	}

}
