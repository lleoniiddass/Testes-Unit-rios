package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	
	private LocacaoService service;
	
	@Parameter
	public List<Filme> filmes;
	
	
	@Parameter(value = 1)
	public Double valorDaLoccao;
	
	@Parameter(value = 2)
	public String cenario;

	private static int num;
	
	@Before
	public void setup() {
		service = new LocacaoService();
		num++;
		System.out.println("Numero " + num);
	}
	
	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros() {
		
		return Arrays.asList(new Object[][] {
			
			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
						   new Filme("Filme 2", 2, 4.0)), 8.0, "2 Filmes: sem desconto"},

			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
						   new Filme("Filme 2", 2, 4.0), 
						   new Filme("Filme 3", 2, 4.0)), 11.0, "3 Filmes: 25%"},
			
			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
						   new Filme("Filme 2", 2, 4.0), 
					       new Filme("Filme 3", 2, 4.0),
					       new Filme("Filme 4", 2, 4.0)), 13.0, "4 Filmes: 50%"},
			
			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
					       new Filme("Filme 2", 2, 4.0), 
					       new Filme("Filme 3", 2, 4.0),
					       new Filme("Filme 4", 2, 4.0),
					       new Filme("Filme 5", 2, 4.0)), 14.0, "5 Filmes: 75%"},
			
			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
				           new Filme("Filme 2", 2, 4.0), 
				           new Filme("Filme 3", 2, 4.0),
				           new Filme("Filme 4", 2, 4.0),
				           new Filme("Filme 5", 2, 4.0),
						   new Filme("Filme 6", 2, 4.0)), 14.0, "6 Filmes: 100%"},

			{Arrays.asList(new Filme("Filme 1", 2, 4.0), 
						   new Filme("Filme 2", 2, 4.0), 
						   new Filme("Filme 3", 2, 4.0),
						   new Filme("Filme 4", 2, 4.0),
						   new Filme("Filme 5", 2, 4.0),
						   new Filme("Filme 6", 2, 4.0),
						   new Filme("Filme 7", 2, 4.0)), 18.0, "7 Filmes: sem desconto"}
		});
	}
	
	@Test
	public void devePagar25PctNoFilme() throws FilmeSemEstoqueException, LocadoraException{

		Usuario usuario = new Usuario("Usuario 1");
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertThat(resultado.getValor(), is(valorDaLoccao));
	}
}
