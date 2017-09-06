package br.com.agenda.financeira.calculo;

public interface ICalculoStrategy<T> {

	Double calcular(T t);

}
