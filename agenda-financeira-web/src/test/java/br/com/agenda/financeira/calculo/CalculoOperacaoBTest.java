package br.com.agenda.financeira.calculo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

@RunWith(MockitoJUnitRunner.class)
public class CalculoOperacaoBTest {
	
	public Conta origem;
	public Conta destino;
	
	@Before 
	public void inicializa() {
		origem = new Conta("023399", "2", "João da Silva");
		destino = new Conta("00332", "1", "Maria da Silva");
	}
	
	@Test
	public void testCalculoOperacao() {
		ICalculoStrategy<Transacao> strategy = new CalculoOperacaoB();
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(8), origem, destino);
		Double taxa = strategy.calcular(transacao);
		assertThat(taxa).isEqualTo(12.0d);
	}

}
