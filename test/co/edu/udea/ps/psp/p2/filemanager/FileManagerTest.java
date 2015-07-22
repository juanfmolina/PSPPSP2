package co.edu.udea.ps.psp.p2.filemanager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class FileManagerTest {
	
	private FileManager fileManager;	
	
	private String path = "D:\\prueba.java";
	private String pathWithOutExtension="D:\\prueba.txt";
	private String pathWithOnlyFiles="D:\\PruebaCarpetaSoloArchivos";
	private String pathDoesntExist="D:\\PruebaCarpetaSoloArchivos2";
	private String pathDirectoryWithOutFiles="D:\\PruebaCarpetaSoloArchivos3";
	private String pathDirectoryWithFilesAndDirectories="D:\\PruebaCarpetaSoloArchivos4";

	@Before
	public void setUp() throws Exception {
		fileManager= new FileManager();
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testGetAllLinesOfDirectoryDoesntExist() throws FileNotFoundException {
			String stringResponse=fileManager.getAllLinesOfDirectory(pathDoesntExist);
			assertEquals("hola mundohola mundohola mundo", stringResponse);
		
	}
	
	@Test
	public void testGetAllLinesOfDirectoryWithOutFiles(){
			String stringResponse;
			try {
				stringResponse = fileManager.getAllLinesOfDirectory(pathDirectoryWithOutFiles);
				assertEquals("", stringResponse);
			} catch (FileNotFoundException e) {
				fail("No se espera esta excepcion");
			}
			
		
	}
	
	@Test
	public void testGetAllLinesOfDirectoryWithFilesAndDirectories(){
			String stringResponse;
			try {
				stringResponse = fileManager.getAllLinesOfDirectory(pathDirectoryWithFilesAndDirectories);
				assertEquals("hola mundohola mundohola mundohola mundohola mundo"
						+ "hola mundohola mundohola mundohola mundohola mundohola mundohola mundo", stringResponse);
			} catch (FileNotFoundException e) {
				fail("No se espera esta excepcion");
			}
			
		
	}

	@Test
	public void testGetAllLinesOfDirectoryWithOnlyFiles() {
		try {
			String stringResponse=fileManager.getAllLinesOfDirectory(pathWithOnlyFiles);
			assertEquals("hola mundohola mundohola mundo", stringResponse);
		} catch (FileNotFoundException e) {
			fail("La carpeta debe existir");
		}
		
	}

	@Test
	public void testValidateExtensionWhenIsTrue() {
		File file = new File(path);
		assertEquals(true, fileManager.validateExtension(file.getName()));
	}
	
	@Test
	public void testValidateExtensionWhenIsFalse() {
		File file = new File(pathWithOutExtension);
		assertEquals(false, fileManager.validateExtension(file.getName()));
	}

}
