package br.com.agenda.financeira.calculo;

import java.math.BigDecimal;

import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoD implements ICalculoStrategy<Transacao> {

	@Override
	public Double calcular(Transacao transacao) {
		if (transacao.getValor().compareTo(BigDecimal.valueOf(1001)) == -1) {
			return new CalculoOperacaoA().calcular(transacao);
		}
		
		if (transacao.getValor().compareTo(BigDecimal.valueOf(1000)) == 1
			 && transacao.getValor().compareTo(BigDecimal.valueOf(2001)) == -1) {
			return new CalculoOperacaoB().calcular(transacao);
		}
		
		if (transacao.getValor().compareTo(BigDecimal.valueOf(2000)) == 1) {
			return new CalculoOperacaoC().calcular(transacao);
		}
		
		throw new RuntimeException("Transacao nao suportada!");
	}

}
