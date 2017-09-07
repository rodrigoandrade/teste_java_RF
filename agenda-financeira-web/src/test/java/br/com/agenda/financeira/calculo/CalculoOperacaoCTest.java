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
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(15), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(82.0d);
	}
	
	@Test
	public void testCalculoOperacaoEntreDezHeVinteDiasComOperacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(1), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testCalculoOperacaoEntreVinteHeTrintaDias() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(25), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(69.0d);
	}
	
	@Test
	public void testCalculoOperacaoEntreVinteHeTrintaDiasComOperacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(2), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testCalculoOperacaoEntreTrintaHeQuarentaDias() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(35), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(47.0d);
	}
	
	@Test
	public void testCalculoOperacaoEntreTrintaHeQuarentaDiasComOperacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(5), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
	@Test
	public void testCalculoOperacaoMaiorQueQuarentaDias() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(45), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		Double taxa = operacao.calcular(transacao);
		
		assertThat(taxa).isEqualTo(17.0d);
	}
	
	@Test
	public void testCalculoOperacaoMaiorQueQuarentaDiasComOperacaoNaoSuportada() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		
		OperacaoEnum operacao = OperacaoEnum.C;
		
		assertThatThrownBy(() -> operacao.calcular(transacao))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Transacao nao suportada!");
	}
	
}
