package br.com.agenda.financeira.calculo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoDTest {

	public Conta origem;
	public Conta destino;
	
	@Before 
	public void inicializa() {
		origem = new Conta("023399", "2", "João da Silva");
		destino = new Conta("00332", "1", "Maria da Silva");
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoA() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(33.0d);
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoANaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(6), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoB() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(2000), LocalDate.now(), LocalDate.now().plusDays(10), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(12.0d);
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoBNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1002), LocalDate.now(), LocalDate.now().plusDays(11), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoC() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(100000), LocalDate.now(), LocalDate.now().plusDays(60), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(1700.0d);
	}
	
	@Test
	public void testCalculoOperacaoComTaxacaoTipoCNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(100), LocalDate.now(), LocalDate.now().plusDays(90), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.D;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
}
