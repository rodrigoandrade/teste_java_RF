package br.com.agenda.financeira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.agenda.financeira.adapter.LocalDateTimeDeserializer;
import br.com.agenda.financeira.adapter.LocalDateTimeSerializer;

public class Transacao {

	@Id
	private String id;
	private BigDecimal valor;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate data;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate agendamento;

	private Conta origem;
	private Conta destino;
	private Double taxa;
	
	public Transacao() {}
	
	public Transacao(BigDecimal valor, LocalDate data, LocalDate agendamento, Conta origem, Conta destino) {

		this.valor = valor;
		this.data = data;
		this.agendamento = agendamento;
		this.origem = origem;
		this.destino = destino;
	}

	public String getId() {
		return id;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalDate getAgendamento() {
		return agendamento;
	}

	public Conta getOrigem() {
		return origem;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
	public Double getTaxa() {
		return taxa;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", valor=" + valor + ", data=" + getData() + ", agendamento=" + agendamento
				+ ", origem=" + origem + ", destino=" + destino + "]";
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
