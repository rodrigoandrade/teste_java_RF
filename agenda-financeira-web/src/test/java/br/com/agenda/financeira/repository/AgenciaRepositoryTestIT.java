package br.com.agenda.financeira.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agenda.financeira.modelo.Agencia;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgenciaRepositoryTestIT {

    @Autowired
    private AgenciaRepository repository;
    
    Agencia agenciaFigueiras;
    Agencia agenciaGoias;
    
    @Before
    public void inicializa() {
       agenciaFigueiras = new Agencia("80828", "1", "Figueiras Santo Andre");
       agenciaGoias = new Agencia("100100", "9", "Goias Sao Caetano");

       repository.save(agenciaGoias);
       repository.save(agenciaFigueiras);
    }
    
    @After
    public void finaliza() {
    	repository.delete(agenciaFigueiras);
    	repository.delete(agenciaGoias);
    }
    
    @Test
    public void testAgendasGravadas() {
        List<Agencia> agencias = repository.findAll();
        assertThat(agencias)
        	.hasSize(2)
        	.extracting("numero", "digito", "nome")
        	.contains(tuple("80828", "1", "Figueiras Santo Andre"),
        		tuple("100100", "9", "Goias Sao Caetano"));
        
    }
    
    @Test
    public void testBuscaPorNumeroDaConta() {
    	Agencia agencia = repository.findByNumero("100100");
    	assertThat(agencia.getDigito()).as("verificando %s' digito", agencia.getDigito()).isEqualTo("9");
    	assertThat(agencia.getNome()).as("verificando %s' nome", agencia.getNome()).isEqualTo("Goias Sao Caetano");
    }
    
    @Test
    public void testBuscaPorNomeDaConta() {
    	Agencia agencia = repository.findByNome("Goias Sao Caetano");
    	assertThat(agencia.getNumero()).as("verificando %s' numero", agencia.getNumero()).isEqualTo("100100");
    	assertThat(agencia.getDigito()).as("verificando %s' digito", agencia.getDigito()).isEqualTo("9");
    }

}
