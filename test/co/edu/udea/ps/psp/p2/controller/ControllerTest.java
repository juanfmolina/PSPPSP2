package co.edu.udea.ps.psp.p2.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.edu.udea.ps.psp.p2.exception.CounterModelException;
import co.edu.udea.ps.psp.p2.model.ClassComponent;
import co.edu.udea.ps.psp.p2.model.MethodComponent;

public class ControllerTest {
	private Controller controller;

	@Before
	public void setUp() throws Exception {
		controller= new Controller();
	}

	@Test(expected= CounterModelException.class)
	public void testGetCountedClassesWithDirectoryNotFound() throws CounterModelException {
		String rootDirectory= "C:\\Users\\ASUS\\git\\PSPPSP3";
		controller.getCountedClasses(rootDirectory);
		
	}
	
	@Test
	public void testGetCountedClassesWithTenClasses() throws CounterModelException {
		String rootDirectory= "C:\\Users\\ASUS\\git\\PSPPSP2";
		List<ClassComponent>classComponents=controller.getCountedClasses(rootDirectory);
		for (ClassComponent classComponent : classComponents) {
			System.out.println(classComponent.getNameOfClass());
			System.out.println("Number of Lines: "+classComponent.getNumberOfLinesOfCode());
			for (MethodComponent methodComponent : classComponent.getMethods()) {
				System.out.println("\t"+methodComponent.getNameOfComponent());
				System.out.println("\tNumber of Lines: "+methodComponent.getNumberOfLinesOfCode());
				System.out.println();
			}
		}
		assertEquals(8, classComponents.size());
		
	}

}
