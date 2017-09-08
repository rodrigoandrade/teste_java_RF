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
import br.com.agenda.financeira.modelo.Conta;
import br.com.agenda.financeira.repository.ContaRepository;

@RunWith(MockitoJUnitRunner.class)
public class ContaServiceTest {

	@InjectMocks
	private ContaService service;
	
	@Mock
	private ContaRepository repository;
	
	private Agencia agencia;
	private Conta conta;
	
	@Before
	public void inicializa() {
		agencia = new Agencia("80828", "1", "Figueiras Santo Andre");
		conta = new Conta("023399", "2", "João da Silva", agencia);
	}
	
	@Test
	public void testSalvar() {
		when(repository.save(conta)).thenReturn(conta);
		service.salva(conta);
		verify(repository).save(conta);
	}
	
	@Test
	public void testBuscaContaPorNumero() {
		when(repository.findByNumero(Mockito.eq(conta.getNumero()))).thenReturn(conta);
		
		Conta contaTeste = service.buscaContaPorNumero(conta.getNumero());
		assertThat(contaTeste.getNumero()).isEqualTo(conta.getNumero());
    	assertThat(contaTeste.getDigito()).isEqualTo(conta.getDigito());
    	assertThat(contaTeste.getTitular()).isEqualTo(conta.getTitular());
    	
    	verify(repository).findByNumero(conta.getNumero());
	}
	
	@Test
	public void testBuscaContaPorTitular() {
		when(repository.findByTitular(Mockito.eq(conta.getTitular()))).thenReturn(conta);
		
		Conta contaTeste = service.buscaContaPorTitular(conta.getTitular());
		assertThat(contaTeste.getNumero()).isEqualTo(conta.getNumero());
    	assertThat(contaTeste.getDigito()).isEqualTo(conta.getDigito());
    	assertThat(contaTeste.getTitular()).isEqualTo(conta.getTitular());
    	
    	verify(repository).findByTitular(conta.getTitular());
	}
}
