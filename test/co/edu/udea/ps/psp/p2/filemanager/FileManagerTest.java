package co.edu.udea.ps.psp.p2.filemanager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class FileManagerTest {

	private FileManager fileManager;

	private String path = "D:\\prueba.java";
	private String pathWithOutExtension = "D:\\prueba.txt";
	private String pathWithOnlyFiles = "D:\\PruebaCarpetaSoloArchivos";
	private String pathDoesntExist = "D:\\PruebaCarpetaSoloArchivos2";
	private String pathDirectoryWithOutFiles = "D:\\PruebaCarpetaSoloArchivos3";
	private String pathDirectoryWithFilesAndDirectories = "D:\\PruebaCarpetaSoloArchivos4";

	@Before
	public void setUp() throws Exception {
		fileManager = new FileManager();
	}

	@Test(expected = FileNotFoundException.class)
	public void testGetAllLinesOfDirectoryDoesntExist() throws FileNotFoundException {
		LinkedList<String> strings = fileManager.getAllLinesOfDirectory(pathDoesntExist);
		assertEquals(3, strings.size());

	}

	@Test
	public void testGetAllLinesOfDirectoryWithOutFiles() {
		try {
			LinkedList<String> strings = fileManager.getAllLinesOfDirectory(pathDirectoryWithOutFiles);
			assertEquals(0, strings.size());
		} catch (FileNotFoundException e) {
			fail("No se espera esta excepcion");
		}

	}

	@Test
	public void testGetAllLinesOfDirectoryWithFilesAndDirectories() {
		try {
			LinkedList<String> strings = fileManager.getAllLinesOfDirectory(pathDirectoryWithFilesAndDirectories);
			assertEquals(12, strings.size());
		} catch (FileNotFoundException e) {
			fail("No se espera esta excepcion");
		}

	}

	@Test
	public void testGetAllLinesOfDirectoryWithOnlyFiles() {
		try {
			LinkedList<String>strings = fileManager.getAllLinesOfDirectory(pathWithOnlyFiles);
			assertEquals(3, strings.size());
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
