package br.com.agenda.financeira.calculo;

import java.math.BigDecimal;

import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoC implements ICalculoStrategy<Transacao> {
	
	@Override
	public Double calcular(Transacao transacao) {
		if (isAgendamentoEntreDezHeVinteDias(transacao)) {
			return transacao.getValor()
					.multiply(BigDecimal.valueOf(8.2))
					.divide(BigDecimal.valueOf(100)).doubleValue();
		}
		
		if (isAgendamentoEntreVinteHeTrintaDias(transacao)) {
			return transacao.getValor()
					.multiply(BigDecimal.valueOf(6.9))
					.divide(BigDecimal.valueOf(100)).doubleValue();
		}
		
		if (isAgendamentoEntreTrintaHeQuarentaDias(transacao)) {
			return transacao.getValor()
					.multiply(BigDecimal.valueOf(4.7))
					.divide(BigDecimal.valueOf(100)).doubleValue();
		}
		
		if (isAgendamentoComMaisDeQuarentaDias(transacao)) {
			return transacao.getValor()
					.multiply(BigDecimal.valueOf(1.7))
					.divide(BigDecimal.valueOf(100)).doubleValue();
		
		} 
		
		throw new RuntimeException("Transacao nao suportada!");
	}
	
	private boolean isAgendamentoEntreDezHeVinteDias(Transacao transacao) {
		return transacao.getAgendamento().isAfter(transacao.getData().plusDays(10))  
				&& transacao.getAgendamento().isBefore(transacao.getData().plusDays(21));
	}

	private boolean isAgendamentoEntreVinteHeTrintaDias(Transacao transacao) {
		return transacao.getAgendamento().isAfter(transacao.getData().plusDays(20))
				&& transacao.getAgendamento().isBefore(transacao.getData().plusDays(31));
	}
	
	private boolean isAgendamentoEntreTrintaHeQuarentaDias(Transacao transacao) {
		return transacao.getAgendamento().isAfter(transacao.getData().plusDays(30))
				&& transacao.getAgendamento().isBefore(transacao.getData().plusDays(41));
	}
	
	private boolean isAgendamentoComMaisDeQuarentaDias(Transacao transacao) {
		return transacao.getAgendamento().isAfter(transacao.getData().plusDays(40));
	}
}
