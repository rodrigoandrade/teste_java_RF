package br.com.agenda.financeira.service;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.agenda.financeira.modelo.Agencia;
import br.com.agenda.financeira.repository.AgenciaRepository;

@RunWith(MockitoJUnitRunner.class)
public class AgenciaServiceTest {
	
	@InjectMocks
	private AgenciaService service;
	
	@Mock
	private AgenciaRepository repository;
	
	private Agencia agencia;
    
    @Before
    public void inicializa() {
       agencia = new Agencia("80828", "1", "Figueiras Santo Andre");
    }
	
    @Test
	public void testSalva() {
    	when(repository.save(Mockito.any(Agencia.class))).thenReturn(agencia);
    	service.salva(agencia);
    	verify(repository).save(agencia);
    }
    
    @Test
    public void testBuscaPorNumero() {
    	when(repository.findByNumero(Mockito.eq(agencia.getNumero()))).thenReturn(agencia);
    	
    	Agencia agenciaTest = service.findByNumero(agencia.getNumero());
    	assertThat(agenciaTest.getNumero()).isEqualTo(agencia.getNumero());
    	assertThat(agenciaTest.getDigito()).isEqualTo(agencia.getDigito());
    	assertThat(agenciaTest.getNome()).isEqualTo(agencia.getNome());
    	
    	verify(repository).findByNumero(agencia.getNumero());
    }
    
    @Test
    public void testBuscaPorNome() {
    	when(repository.findByNome(Mockito.eq(agencia.getNome()))).thenReturn(agencia);
    	
    	Agencia agenciaTest = service.findByNome(agencia.getNome());
    	assertThat(agenciaTest.getNumero()).isEqualTo(agencia.getNumero());
    	assertThat(agenciaTest.getDigito()).isEqualTo(agencia.getDigito());
    	assertThat(agenciaTest.getNome()).isEqualTo(agencia.getNome());
    	
    	verify(repository).findByNome(agencia.getNome());
    }
}
