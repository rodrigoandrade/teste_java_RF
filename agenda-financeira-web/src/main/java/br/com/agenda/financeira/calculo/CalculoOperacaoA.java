package br.com.agenda.financeira.calculo;

import java.math.BigDecimal;

import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoA implements ICalculoStrategy<Transacao> {

	@Override
	public Double calcular(Transacao transacao) {
		
		if (transacao.getData().isEqual(transacao.getAgendamento())) {
			return transacao.getValor()
					.multiply(BigDecimal.valueOf(3))
					.divide(BigDecimal.valueOf(100))
					.add(BigDecimal.valueOf(3)).doubleValue();
		}
		throw new RuntimeException("Transacao nao suportada!");
	}

}
