package br.com.agenda.financeira.modelo;

import java.util.List;

public class Agencia {

	private String numero;
	private String digito;
	private List<Conta> contas;

	public Agencia(String numero, String digito, List<Conta> contas) {
		this.numero = numero;
		this.digito = digito;
		this.contas = contas;
	}

}
