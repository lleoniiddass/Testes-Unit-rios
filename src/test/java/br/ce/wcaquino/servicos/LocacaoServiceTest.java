package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import junit.framework.Assert;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeLocacao() throws Exception {
		
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		Filme filme = new Filme("FILME 1", 1, 5.0);
		
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

		
//CoreMatchers
//		assertThat(locacao.getValor(), is(equalTo(5.0)));
//		assertThat(locacao.getValor(), is(not(6.0)));
//		assertEquals(5.0, locacao.getValor(), 0.01);
//		assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
//		assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));
	}
	
	@Test(expected = Exception.class)
	public void testeLocacaoSemEstoque() throws Exception {
		
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		Filme filme = new Filme("FILME 1", 0, 5.0);
		
		locacaoService.alugarFilme(usuario, filme);

	}
	
	@Test
	public void testeLocacaoSemEstoque2() {
		
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		Filme filme = new Filme("FILME 1", 0, 5.0);
		
		try {			
			locacaoService.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lancado exceção");//caso se nenhum exceção seja lançada vai cai nessa linha
		} catch (Exception e) {
			assertThat(e.getMessage(), is("filme sem estoque"));
		}
	}
	
	@Test
	public void testeLocacaoSemEstoque3() throws Exception {
		
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		Filme filme = new Filme("FILME 1", 0, 5.0);
		
		exception.expect(Exception.class);
		exception.expectMessage("filme sem estoque");

		locacaoService.alugarFilme(usuario, filme);

	}
}
