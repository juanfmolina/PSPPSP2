package co.edu.udea.ps.psp.p2.countermodel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CounterModelTest {
	private CounterModel counter;
	
	@Before
	public void setUp() throws Exception {
		counter= new CounterModel();
	}

	@Test
	public void testIsPackage() {
		String possiblePAckage="package co999.cp;";
			assertTrue(counter.isPackage(possiblePAckage));
		
	}
	
	@Test
	public void testIsImports() {
		String possibleImport="import co999;import co999.cp;import co999.cp;";
			assertTrue(counter.isImports(possibleImport));
		
	}
	
	@Test
	public void testIsClass() {
		String possibleClass="   public class jUaNFER_NaNDO      {juan}";
			assertTrue(counter.isClass(possibleClass));
		
	}
	
	@Test
	public void testIsMethod() {
		String possibleMethod="public void testIsMethod() {String possibleMethod=\"private static final int<444>main(oelo o osdf) {}\";assertTrue(counter.isMethod(possibleMethod));}";
			assertTrue(counter.isMethod(possibleMethod));
		
	}

}
