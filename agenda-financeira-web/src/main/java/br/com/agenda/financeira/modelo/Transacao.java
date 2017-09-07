package br.com.agenda.financeira.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {

	private BigDecimal valor;
	private LocalDate data;
	private LocalDate agendamento;
	private Conta origem;
	private Conta destino;
	
	public Transacao(BigDecimal valor, LocalDate data, LocalDate agendamento, Conta origem,
			Conta destino) {

		this.valor = valor;
		this.data = data;
		this.agendamento = agendamento;
		this.origem = origem;
		this.destino = destino;
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

}
