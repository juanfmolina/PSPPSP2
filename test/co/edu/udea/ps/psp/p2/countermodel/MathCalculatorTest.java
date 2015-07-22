package co.edu.udea.ps.psp.p2.countermodel;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import co.edu.udea.ps.psp.p2.countermodel.MathCalculator;
import co.edu.udea.ps.psp.p2.exception.MathModelException;

public class MathCalculatorTest {
	private MathCalculator math;

	@Before
	public void setUp() throws Exception {
		math= new MathCalculator();
		
	}

	@Test
	public void testMeanWithEmptyList() {
		boolean thrown = false;
		LinkedList<Double> numbers = new LinkedList<>();
		try {
			double mean=math.calculateMean(numbers);
		} catch (MathModelException e) {
			thrown=true;
		}
		assertEquals(true, thrown);
		
	}
	
	@Test
	public void testMeanWithTwoNumbers() {
		double mean=0;
		LinkedList<Double> numbers = new LinkedList<>();
		numbers.add(2d);
		numbers.add(3d);
		try {
			mean=math.calculateMean(numbers);
		} catch (MathModelException e) {
			fail("La lista no debe estar vacía");
		}
		assertEquals(2.5,mean,0.001);
		
	}
	
	@Test
	public void testMeanWithThreeNumbers() {
		double mean=0;
		LinkedList<Double> numbers = new LinkedList<>();
		numbers.add(2d);
		numbers.add(3d);
		numbers.add(2.4);
		try {
			mean=math.calculateMean(numbers);
		} catch (MathModelException e) {
			fail("La lista no debe estar vacía");
		}
		assertEquals(2.4667,mean,0.0001);
		
	}
	
	@Test
	public void testStandarDesviationWithEmptyList() {
		boolean thrown = false;
		LinkedList<Double> numbers = new LinkedList<>();
		try {
			double sd=math.calculateStandarDesviation(numbers);
		} catch (MathModelException e) {
			thrown=true;
		}
		assertEquals(true, thrown);
		
	}
	
	@Test
	public void testStandarDesviationWithTwoNumbers() {
		double sd=0;
		LinkedList<Double> numbers = new LinkedList<>();
		numbers.add(2d);
		numbers.add(3d);
		try {
			sd=math.calculateStandarDesviation(numbers);
		} catch (MathModelException e) {
			fail("La lista no debe estar vacía");
		}
		assertEquals(0.70711,sd,0.001);
		
	}
	
	@Test
	public void testStandarDesviationWithThreeNumbers() {
		double sd=0;
		LinkedList<Double> numbers = new LinkedList<>();
		numbers.add(2d);
		numbers.add(3d);
		numbers.add(2.4);
		try {
			sd=math.calculateStandarDesviation(numbers);
		} catch (MathModelException e) {
			fail("La lista no debe estar vacía");
		}
		assertEquals(0.50332,sd,0.0001);
		
	}

}
