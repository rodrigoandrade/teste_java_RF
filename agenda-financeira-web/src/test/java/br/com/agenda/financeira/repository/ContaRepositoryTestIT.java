package br.com.agenda.financeira.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaRepositoryTestIT {

    @Autowired
    private ContaRepository contaRepository;
    
    @Autowired
    private AgenciaRepository agenciaRepository;
    
    private Agencia agenciaOrigem;
    private Agencia agenciaDestino;
    
    private Conta origem;
	private Conta destino;
    
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
    }
    
    @After
    public void finaliza() {
    	agenciaRepository.delete(agenciaOrigem);
    	agenciaRepository.delete(agenciaDestino);
    	
    	contaRepository.delete(origem);
    	contaRepository.delete(destino);
    }
    
    @Test
    public void testContasGravadas() {
        List<Conta> contas = contaRepository.findAll();
        assertThat(contas)
        	.hasSize(2)
        	.extracting("numero", "digito", "titular")
        	.contains(tuple("023399", "2", "João da Silva"),
        		tuple("00332", "1", "Maria da Silva"));
        
    }
    
    @Test
    public void testBuscaPorNumeroDaConta() {
    	Conta conta = contaRepository.findByNumero("00332");
    	assertThat(conta.getDigito()).as("verificando %s' digito", conta.getDigito()).isEqualTo("1");
    	assertThat(conta.getTitular()).as("verificando %s' titular", conta.getTitular()).isEqualTo("Maria da Silva");
    	assertThat(conta.getAgencia().getNumero()).as("verificando %s' numero", conta.getAgencia().getNumero()).isEqualTo("100100");
    	assertThat(conta.getAgencia().getDigito()).as("verificando %s' digito", conta.getAgencia().getDigito()).isEqualTo("9");
    	assertThat(conta.getAgencia().getNome()).as("verificando %s' nome", conta.getAgencia().getNome()).isEqualTo("Goias Sao Caetano");
    }
    
    @Test
    public void testBuscaPorTitularDaConta() {
    	Conta conta = contaRepository.findByTitular("Maria da Silva");
    	assertThat(conta.getNumero()).as("verificando %s' numero", conta.getNumero()).isEqualTo("00332");
    	assertThat(conta.getDigito()).as("verificando %s' digito", conta.getDigito()).isEqualTo("1");
    	assertThat(conta.getAgencia().getNumero()).as("verificando %s' numero", conta.getAgencia().getNumero()).isEqualTo("100100");
    	assertThat(conta.getAgencia().getDigito()).as("verificando %s' digito", conta.getAgencia().getDigito()).isEqualTo("9");
    	assertThat(conta.getAgencia().getNome()).as("verificando %s' nome", conta.getAgencia().getNome()).isEqualTo("Goias Sao Caetano");
    }

}
