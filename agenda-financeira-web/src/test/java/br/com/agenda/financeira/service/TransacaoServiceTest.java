package br.com.agenda.financeira.service;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.repository.TransacaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService service;

	@Mock
	private TransacaoRepository repository;

	private Agencia agencia;
	private Conta contaOrigem;
	private Conta contaDestino;
	private Transacao transacao;
	
	@Before
	public void inicializa() {
		agencia = new Agencia("80828", "1", "Figueiras Santo Andre");
		contaOrigem = new Conta("023399", "2", "João da Silva", agencia);
		contaDestino = new Conta("023399", "2", "João da Silva", agencia);
		transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), contaOrigem, contaDestino);
	}
	
	@Test
	public void testSalva() {
		OperacaoEnum operacao = OperacaoEnum.A; 
		transacao.setTaxa(operacao.calcular(transacao));
		
		when(repository.save(transacao)).thenReturn(transacao);
		
		service.salva(OperacaoEnum.A.name(), transacao);
		
		verify(repository).save(transacao);
	}
	
	@Test
	public void testSalvaComTransacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(5), contaOrigem, contaDestino);
		
		assertThatThrownBy(() -> service.salva(OperacaoEnum.A.name(), transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testBuscaPorData() throws Exception {
		LocalDate hoje = LocalDate.now();
		when(repository.findByData(Mockito.eq(hoje))).thenReturn(Arrays.asList(transacao));
		
		List<Transacao> transacoes = service.buscaPorData(hoje);
		assertThat(transacoes)
    		.hasSize(1)
    		.extracting("valor", "data", "agendamento")
    		.contains(tuple(BigDecimal.valueOf(1000), hoje, hoje));
		
		verify(repository).findByData(hoje);
	}
	
	@Test
	public void testBuscaPorDataDeAgendamento() throws Exception {
		LocalDate hojeMaisCincoDias = LocalDate.now().plusDays(5);
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), hojeMaisCincoDias, contaOrigem, contaDestino);
		
		when(repository.findByAgendamento(Mockito.eq(hojeMaisCincoDias))).thenReturn(Arrays.asList(transacao));
		
		List<Transacao> transacoes = service.buscaPorAgendamento(hojeMaisCincoDias);
		assertThat(transacoes)
    		.hasSize(1)
    		.extracting("valor", "data", "agendamento")
    		.contains(tuple(BigDecimal.valueOf(1000), LocalDate.now(), hojeMaisCincoDias));
		
		verify(repository).findByAgendamento(hojeMaisCincoDias);
	}
	
	@Test
	public void testBuscaPorDataHeDataDeAgendamento() throws Exception {
		LocalDate hoje = LocalDate.now();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), hoje, hoje, contaOrigem, contaDestino);
		
		when(repository.findByDataAndAgendamento(Mockito.eq(hoje), Mockito.eq(hoje))).thenReturn(Arrays.asList(transacao));
		
		List<Transacao> transacoes = service.buscaPorDataHeDataAgendamento(hoje, hoje);
		assertThat(transacoes)
    		.hasSize(1)
    		.extracting("valor", "data", "agendamento")
    		.contains(tuple(BigDecimal.valueOf(1000), hoje, hoje));
		
		verify(repository).findByDataAndAgendamento(hoje, hoje);
	}
	
}
