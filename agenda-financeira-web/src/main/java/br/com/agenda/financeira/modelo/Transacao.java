package br.com.agenda.financeira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Transacao {

	@Id
	private String id;
	private BigDecimal valor;
	private LocalDate data;
	private LocalDate agendamento;
	private Conta origem;
	private Conta destino;
	private Double taxa;
	
	public Transacao(BigDecimal valor, LocalDate data, LocalDate agendamento, Conta origem,
			Conta destino) {

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
		return "Transacao [id=" + id + ", valor=" + valor + ", data=" + data + ", agendamento=" + agendamento
				+ ", origem=" + origem + ", destino=" + destino + "]";
	}
}
