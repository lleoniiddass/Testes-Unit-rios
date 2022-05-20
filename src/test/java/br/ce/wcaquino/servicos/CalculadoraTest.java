package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZero;

public class CalculadoraTest {
	
	private Calculadora cal;
	
	@Before
	public void setup() {
		cal = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		int a = 3, b = 5;
		
		
		
		int resultado = cal.somar(a, b);
		
		Assert.assertEquals(8, resultado);
	}
	
	
	@Test
	public void deveSubtrairDoisValores() {
		
		int a = 5, b = 3;
		
		int resultado = cal.subtrair(a, b);
		
		Assert.assertEquals(2, resultado);
	}
	
	@Test
	public void deveDivirDoisValores() {
		
		int a = 6, b = 3;
		
		int resultado = cal.dive(a, b);
		
		Assert.assertEquals(2, resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZero.class)
	public void deveLancarException() throws NaoPodeDividirPorZero {
		
		int a = 10, b = 0;
		
		int resultado = cal.dive(a, b);
		
		Assert.assertEquals(2, resultado);
	}
	
}
