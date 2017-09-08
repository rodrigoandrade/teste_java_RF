package br.com.agenda.financeira.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.modelo.Transacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoRepositoryTestIT {
	
	@Autowired
    private ContaRepository contaRepository;
    
    @Autowired
    private AgenciaRepository agenciaRepository;
    
    @Autowired
    private TransacaoRepository transacaoRepository;
    
    private Agencia agenciaOrigem;
    private Agencia agenciaDestino;
    
    private Conta origem;
	private Conta destino;
	
	private Transacao transacao1;
	private Transacao transacao2;
	private Transacao transacao3;
	private Transacao transacao4;
	private Transacao transacao5;
    
    @Before
    public void inicializa() {
    	agenciaOrigem = new Agencia("80828", "1", "Figueiras Santo Andre");
    	agenciaDestino = new Agencia("100100", "9", "Goias Sao Caetano");

    	agenciaRepository.save(agenciaOrigem);
    	agenciaRepository.save(agenciaDestino);
    	
    	origem = new Conta("023399", "2", "João da Silva", agenciaOrigem);
    	destino = new Conta("00332", "1", "Maria da Silva", agenciaDestino);
    	
    	contaRepository.save(origem);
    	contaRepository.save(destino);
    	
    	transacao1 = new Transacao(BigDecimal.valueOf(30000), LocalDate.now(), LocalDate.now().plusDays(60), origem, destino);
    	transacao2 = new Transacao(BigDecimal.valueOf(6230), LocalDate.now(), LocalDate.now().plusDays(13), origem, destino);
    	transacao3 = new Transacao(BigDecimal.valueOf(1229), LocalDate.now().plusDays(10), LocalDate.now().plusDays(30), origem, destino);
    	transacao4 = new Transacao(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(3), origem, destino);
    	transacao5 = new Transacao(BigDecimal.valueOf(499), LocalDate.now(), LocalDate.now(), origem, destino);
    	
    	transacaoRepository.save(transacao1);
    	transacaoRepository.save(transacao2);
    	transacaoRepository.save(transacao3);
    	transacaoRepository.save(transacao4);
    	transacaoRepository.save(transacao5);
    }
    
    @After
    public void finaliza() {
    	agenciaRepository.delete(agenciaOrigem);
    	agenciaRepository.delete(agenciaDestino);
    	
    	contaRepository.delete(origem);
    	contaRepository.delete(destino);
    	
    	transacaoRepository.delete(transacao1);
    	transacaoRepository.delete(transacao2);
    	transacaoRepository.delete(transacao3);
    	transacaoRepository.delete(transacao4);
    	transacaoRepository.delete(transacao5);
    }
    
    @Test
    public void testBuscaPorDataDeTransacao() {
    	List<Transacao> transacoes = transacaoRepository.findByData(LocalDate.now());
        assertThat(transacoes)
        	.hasSize(4)
        	.extracting("valor", "data", "agendamento")
        	.contains(tuple(BigDecimal.valueOf(30000), LocalDate.now(), LocalDate.now().plusDays(60)),
        			tuple(BigDecimal.valueOf(6230), LocalDate.now(), LocalDate.now().plusDays(13)),
        			tuple(BigDecimal.valueOf(1000), LocalDate.now(), LocalDate.now().plusDays(3)),
        			tuple(BigDecimal.valueOf(499), LocalDate.now(), LocalDate.now()));
    }
	
    @Test
	public void testBuscaPorDataDeAgendamento() {
		List<Transacao> transacoes = transacaoRepository.findByAgendamento(LocalDate.now());
        assertThat(transacoes)
        	.hasSize(1)
        	.extracting("valor", "data", "agendamento")
        	.contains(tuple(BigDecimal.valueOf(499), LocalDate.now(), LocalDate.now()));
	}

    @Test
	public void testPorDataTransacaoHeDataAgendamento() {
    	List<Transacao> transacoes = transacaoRepository.findByDataAndAgendamento(LocalDate.now().plusDays(10), LocalDate.now().plusDays(30));
        assertThat(transacoes)
        	.hasSize(1)
        	.extracting("valor", "data", "agendamento")
        	.contains(tuple(BigDecimal.valueOf(1229), LocalDate.now().plusDays(10), LocalDate.now().plusDays(30)));

	}

}
