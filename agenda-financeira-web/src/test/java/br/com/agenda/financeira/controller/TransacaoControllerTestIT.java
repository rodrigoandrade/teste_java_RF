package br.com.agenda.financeira.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.service.TransacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransacaoControllerTestIT {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@LocalServerPort
	private int port;
	
	HttpHeaders headers = new HttpHeaders();

	private Conta origem;
	private Conta destino;
	private List<Transacao> transacoes;
	
	@Before
	public void inicializa() {
		Agencia goias = new Agencia("31999", "1", "Goias Sao Caetano");
		Agencia domPedro = new Agencia("101011", "2", "Dom Pedro II");
		
		origem = new Conta("404", "2", "Maria", goias);
		destino = new Conta("319", "1", "Jose", domPedro);

		transacoes = new ArrayList<>();
		Transacao transacaoB = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(4), origem, destino);
		Transacao transacaoA = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		Transacao transacaoC = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(40), origem, destino);
		Transacao transacaoD = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);

		transacoes.add(transacaoA);
		transacoes.add(transacaoB);
		transacoes.add(transacaoC);
		transacoes.add(transacaoD);
		
		transacaoService.salva(OperacaoEnum.B.name(), transacaoB);
		transacaoService.salva(OperacaoEnum.A.name(), transacaoA);
		transacaoService.salva(OperacaoEnum.C.name(), transacaoC);
		transacaoService.salva(OperacaoEnum.D.name(), transacaoD);
	}
	
	@After
	public void finaliza() {
		transacoes.forEach(transacao -> {
			transacaoService.deleta(transacao);
		});
	}
	
	@Test
    public void testGetTodasTransacoes() {
		transacoes.forEach(transacao -> {
			HttpEntity<Transacao> entity = new HttpEntity<Transacao>(transacao, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(criaURL("/transacoes"), HttpMethod.GET, entity, String.class);
			assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		});
    }
	
	@Test
	public void testGetTransacoesPorDataDeAgendamento() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(4), origem, destino);
		HttpEntity<Transacao> entity = new HttpEntity<Transacao>(transacao, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				criaURL("/transacoes?dataAgendamento="+transacao.getAgendamento()), HttpMethod.GET, entity, String.class);
		
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testGetTransacoesPorDataDeTransacao() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		HttpEntity<Transacao> entity = new HttpEntity<Transacao>(transacao, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				criaURL("/transacoes?data="+transacao.getData()), HttpMethod.GET, entity, String.class);
		
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testGetTransacoesPorDataDeAgendamentoSemConteudo() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(90), origem, destino);
		HttpEntity<Transacao> entity = new HttpEntity<Transacao>(transacao, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				criaURL("/transacoes?dataAgendamento="+transacao.getAgendamento()), HttpMethod.GET, entity, String.class);
		
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void testSalvar() throws Exception {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now().plusDays(90), LocalDate.now().plusDays(90), origem, destino);
		HttpEntity<Transacao> entity = new HttpEntity<Transacao>(transacao, headers);
			
		ResponseEntity<String> response = restTemplate.exchange(
				criaURL("/transacoes?tipoOperacao="+OperacaoEnum.A.name()),
				HttpMethod.POST, entity, String.class);
		
		Transacao transacaoSalva = transacaoService.buscaPorData(LocalDate.now().plusDays(90)).get(0);
		transacoes.add(transacaoSalva);
		
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	private String criaURL(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
