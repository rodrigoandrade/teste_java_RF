package br.com.agenda.financeira.operacao;

public interface IOperacaoStrategy<T> {

	Double calcular(T t);

}
