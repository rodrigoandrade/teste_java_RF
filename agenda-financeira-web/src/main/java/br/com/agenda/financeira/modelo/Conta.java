package br.com.agenda.financeira.modelo;

public class Conta {
	
	private String numero;
	private String digito;
	private String titular;
	
	public Conta(String numero, String digito, String titular) {
		this.numero = numero;
		this.digito = digito;
		this.titular = titular;
	}
	
}
