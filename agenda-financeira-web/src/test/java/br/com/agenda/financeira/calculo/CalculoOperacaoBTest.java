package br.com.agenda.financeira.calculo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.agenda.financeira.enums.OperacaoEnum;
import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoBTest {
	
	public Conta origem;
	public Conta destino;
	
	private Agencia agenciaOrigem;
	private Agencia agenciaDestino;
	
	@Before 
	public void inicializa() {
		agenciaOrigem = new Agencia("80828", "1", "Figueiras Santo Andre");
		agenciaDestino = new Agencia("100100", "9", "Goias Sao Caetano");
		
		origem = new Conta("023399", "2", "João da Silva", agenciaOrigem);
		destino = new Conta("00332", "1", "Maria da Silva", agenciaDestino);
	}
	
	@Test
	public void testCalculoOperacao() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(8), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.B;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(12.0d);
	}

	@Test
	public void testCalculoOperacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(20), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.B;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
}
