package br.com.agenda.financeira.calculo;

import br.com.agenda.financeira.modelo.Transacao;

public class Calculo {

	private ICalculoStrategy<Transacao> operacao;
	
	public Calculo(ICalculoStrategy<Transacao> operacao) {
		this.operacao = operacao;
	}
	
	public Double calcularOperacao(Transacao transacao) {
		return operacao.calcular(transacao);
	}
	
}
