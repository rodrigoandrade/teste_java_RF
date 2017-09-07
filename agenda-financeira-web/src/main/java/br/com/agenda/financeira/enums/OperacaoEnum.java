package br.com.agenda.financeira.enums;

import br.com.agenda.financeira.calculo.CalculoOperacaoA;
import br.com.agenda.financeira.calculo.CalculoOperacaoB;
import br.com.agenda.financeira.calculo.CalculoOperacaoC;
import br.com.agenda.financeira.calculo.CalculoOperacaoD;
import br.com.agenda.financeira.calculo.ICalculoStrategy;
import br.com.agenda.financeira.modelo.Transacao;

public enum OperacaoEnum implements ICalculoStrategy<Transacao> {
	
	A {
		@Override
		public Double calcular(Transacao transacao) {
			return new CalculoOperacaoA().calcular(transacao);
		}
	}, 
	
	B {
		@Override
		public Double calcular(Transacao transacao) {
			return new CalculoOperacaoB().calcular(transacao);
		}
	}, 
	
	C {
		@Override
		public Double calcular(Transacao transacao) {
			return new CalculoOperacaoC().calcular(transacao);
		}
	}, 
	
	D {
		@Override
		public Double calcular(Transacao transacao) {
			return new CalculoOperacaoD().calcular(transacao);
		}
	};

}
