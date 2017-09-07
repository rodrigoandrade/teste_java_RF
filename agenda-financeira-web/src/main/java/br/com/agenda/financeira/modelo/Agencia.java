package br.com.agenda.financeira.modelo;

import org.springframework.data.annotation.Id;

public class Agencia {

	@Id
	private String id;
	private String numero;
	private String digito;
	private String nome;

	public Agencia(String numero, String digito, String nome) {
		this.numero = numero;
		this.digito = digito;
		this.nome = nome;
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

	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "Agencia [id=" + id + ", numero=" + numero + ", digito=" + digito + ", nome=" + nome + "]";
	}

}
