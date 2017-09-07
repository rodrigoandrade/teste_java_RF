package br.com.agenda.financeira.calculo;

import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoB implements ICalculoStrategy<Transacao> {

	@Override
	public Double calcular(Transacao transacao) {
		if (transacao.getAgendamento().isBefore(transacao.getData().plusDays(11))) {
			return 12d;
		}
		throw new RuntimeException("Transacao nao suportada!");
	}

}
