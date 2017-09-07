package br.com.agenda.financeira.calculo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

public class OperacaoTest {
	
	public Conta origem;
	public Conta destino;
	
	@Before 
	public void inicializa() {
		origem = new Conta("023399", "2", "João da Silva");
		destino = new Conta("00332", "1", "Maria da Silva");
	}
	
	@Test
	public void testOperacaoCalculoOperacaoA() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		Double taxa = new Operacao().buscaTaxaPor(transacao);
		assertThat(taxa).isEqualTo(33.0d);
	}
	
	@Test
	public void testOperacaoCalculoOperacaoB() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(4), origem, destino);
		Double taxa = new Operacao().buscaTaxaPor(transacao);
		assertThat(taxa).isEqualTo(12.0d);
	}
	
	@Test
	public void testOperacaoCalculoOperacaoC() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(14), origem, destino);
		Double taxa = new Operacao().buscaTaxaPor(transacao);
		assertThat(taxa).isEqualTo(82.0d);
	}
}
