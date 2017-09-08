package br.com.agenda.financeira.modelo;

import org.springframework.data.annotation.Id;

public class Conta {
	
	@Id
	private String id;
	private String numero;
	private String digito;
	private String titular;
	
	private Agencia agencia;
	
	public Conta(String numero, String digito, String titular, Agencia agencia) {
		this.numero = numero;
		this.digito = digito;
		this.titular = titular;
		this.agencia = agencia;
	}

	public String getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getDigito() {
		return digito;
	}

	public String getTitular() {
		return titular;
	}

	public Agencia getAgencia() {
		return agencia;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", numero=" + numero + ", digito=" + digito + ", titular=" + titular + ", agencia="
				+ agencia + "]";
	}

}
