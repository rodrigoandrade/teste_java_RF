package br.com.agenda.financeira.calculo;

import br.com.agenda.financeira.modelo.Transacao;

public class Operacao {

	public Double buscaTaxaPor(Transacao transacao) {
		if (transacao.getData().isEqual(transacao.getAgendamento())) {
			ICalculoStrategy<Transacao> calculo = new CalculoOperacaoA();
			return calculo.calcular(transacao);
		}
		if (transacao.getAgendamento().isBefore(transacao.getData().plusDays(11))) {
			ICalculoStrategy<Transacao> calculo = new CalculoOperacaoB();
			return calculo.calcular(transacao);
		}
		if (transacao.getAgendamento().isAfter(transacao.getData().plusDays(10))) {
			ICalculoStrategy<Transacao> calculo = new CalculoOperacaoC();
			return calculo.calcular(transacao);
		}
		return null;
	}
	
}
