package br.com.agenda.financeira.calculo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoCTest {

	public Conta origem;
	public Conta destino;
	
	@Before 
	public void inicializa() {
		origem = new Conta("023399", "2", "João da Silva");
		destino = new Conta("00332", "1", "Maria da Silva");
	}
	
	@Test
	public void testCalculoOperacaoEntreDezHeVinteDias() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoC();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(15), origem, destino);
		Double taxa = strategy.calcular(transacao);
		assertThat(taxa).isEqualTo(82.0d);
	}
	
	@Test
	public void testCalculoOperacaoEntreVinteHeTrintaDias() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoC();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(30), origem, destino);
		Double taxa = strategy.calcular(transacao);
		assertThat(taxa).isEqualTo(69.0d);
	}
	
	@Test
	public void testCalculoOperacaoEntreTrintaHeQuarentaDias() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoC();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(39), origem, destino);
		Double taxa = strategy.calcular(transacao);
		assertThat(taxa).isEqualTo(47.0d);
	}
	
	@Test
	public void testCalculoOperacaoMaiorQueQuarentaDias() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoC();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(41), origem, destino);
		Double taxa = strategy.calcular(transacao);
		assertThat(taxa).isEqualTo(17.0d);
	}
	
	@Test
	public void testCalculoOperacaoComExcecao() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoC();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		
		assertThatThrownBy(() -> strategy.calcular(transacao))
				.isInstanceOf(RuntimeException.class)
				.hasMessage("Nao achou operacao de calculo para essa transacao!");
	}
	
}
