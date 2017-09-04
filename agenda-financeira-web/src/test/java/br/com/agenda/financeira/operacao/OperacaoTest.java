package br.com.agenda.financeira.operacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.*;

import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;
import br.com.agenda.financeira.operacao.IOperacaoStrategy;

@RunWith(MockitoJUnitRunner.class)
public class OperacaoTest {
	
	public Conta origem;
	public Conta destino;
	
	@Mock
	IOperacaoStrategy<Transacao> operation;
	
	@Before
	public void inicializa() {
		origem = new Conta("023399", "2", "João da Silva");
		destino = new Conta("00332", "1", "Maria da Silva");
	}
	
	@Test
	public void testOperacaoTipoA() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now(), origem, destino);
		when(operation.calcular(transacao)).thenReturn(33.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(33.0d);
	}
	
	@Test
	public void testOperacaoTipoB() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(4), origem, destino);
		when(operation.calcular(transacao)).thenReturn(12.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(12.0d);
	}
	
	@Test
	public void testOperacaoTipoCAcimaDeDezDiasDaDataDeAgendamento() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(12), origem, destino);
		when(operation.calcular(transacao)).thenReturn(82.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(82.0d);
	}
	
	@Test
	public void testOperacaoTipoCAcimaDeVinteDiasDaDataDeAgendamento() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(28), origem, destino);
		when(operation.calcular(transacao)).thenReturn(69.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(69.0d);
	}
	
	@Test
	public void testOperacaoTipoCAcimaDeTrintaDiasDaDataDeAgendamento() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(33), origem, destino);
		when(operation.calcular(transacao)).thenReturn(47.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(47.0d);
	}
	
	@Test
	public void testOperacaoTipoCAcimaDeQuarentaDiasDaDataDeAgendamento() {
		Transacao transacao = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(60), origem, destino);
		when(operation.calcular(transacao)).thenReturn(17.0d);
		
		double taxa = operation.calcular(transacao);
		assertThat(taxa).isEqualTo(17.0d);
	}
	
}
