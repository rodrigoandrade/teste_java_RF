package br.com.agenda.financeira.calculo;

import br.com.agenda.financeira.modelo.Transacao;

public class CalculoOperacaoB implements ICalculoStrategy<Transacao> {

	@Override
	public Double calcular(Transacao transacao) {
		return 12d;
	}

}
