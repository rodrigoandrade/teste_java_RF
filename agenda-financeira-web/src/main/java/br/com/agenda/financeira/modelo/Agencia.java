package br.com.agenda.financeira.modelo;

import org.springframework.data.annotation.Id;

public class Agencia {

	@Id
	private String id;
	private String numero;
	private String digito;
	private String nome;

	public Agencia() {}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agencia other = (Agencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
