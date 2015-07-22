package co.edu.udea.ps.psp.p2.controller;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import co.edu.udea.ps.psp.p2.controller.Controller;
import co.edu.udea.ps.psp.p2.exception.MathModelException;

public class ControllerTest {

	private Controller controller;

	private String path = "D:\\prueba.txt";
	private String pathNoExist= "D:\\prueba2.txt";
	private String pathBadNumbers= "D:\\prueba3.txt";
	
	@Before
	public void setUp() throws Exception {
		controller= new Controller();
	}

	@Test
	public void testGetNumbersFromFileControllerFileDoesntExist() {
		
		LinkedList<Double> numbers;
		try {
			numbers = controller.getnumbersFromFile(pathNoExist);
		} catch (MathModelException e) {
			assertEquals("El archivo no existe", e.getMessage());
		}
	}
	
	@Test
	public void testGetNumbersFromFileControllerThreeNumbers() {
		
		LinkedList<Double> numbers;
		try {
			numbers = controller.getnumbersFromFile(path);
			assertEquals(3, numbers.size());
		} catch (MathModelException e) {
			assertEquals("El archivo no existe", e.getMessage());
		}
	}
	
	@Test
	public void testCalculateMean() {
		
		double mean= 0;
		try {
			
			mean = controller.calculateMean(controller.getnumbersFromFile(path));
			assertEquals(2.1666666, mean, 0.0001);
		} catch (MathModelException e) {
			assertEquals("El archivo no existe", e.getMessage());
		}
	}
	
	@Test
	public void testCalculateStandardDesviation() {
		
		double mean= 0;
		try {
			
			mean = controller.calculateStandardDesviation(controller.getnumbersFromFile(path));
			assertEquals(1.258305, mean, 0.0001);
		} catch (MathModelException e) {
			assertEquals("El archivo no existe", e.getMessage());
		}
	}

}
