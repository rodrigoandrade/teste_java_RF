package br.com.agenda.financeira.operacao;

import br.com.agenda.financeira.modelo.Transacao;

public class Operacao {

	private IOperacaoStrategy<Transacao> operacao;
	
	public Operacao(IOperacaoStrategy<Transacao> operacao) {
		this.operacao = operacao;
	}
	
	public Double calcularOperacao(Transacao transacao) {
		return operacao.calcular(transacao);
	}
	
}
