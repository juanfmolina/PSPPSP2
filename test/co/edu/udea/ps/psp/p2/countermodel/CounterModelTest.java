package co.edu.udea.ps.psp.p2.countermodel;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.edu.udea.ps.psp.p2.model.ClassComponent;
import co.edu.udea.ps.psp.p2.model.MethodComponent;

public class CounterModelTest {
	private CounterModel counter;

	@Before
	public void setUp() throws Exception {
		counter = new CounterModel();
	}

	@Test
	public void testIsPackage() {
		String possiblePAckage = "package co999.cp;";
		assertTrue(counter.isPackage(possiblePAckage));

	}
	
	@Test
	public void testIsParameters() {
		String possibleParameters = "(oelo<oelo> oelo, oelo<oelo> oelo)";
		assertTrue(counter.areParameters(possibleParameters));

	}

	@Test
	public void testIsImports() {
		String possibleImport = "import co999;import co999.cp;import co999.cp;";
		assertTrue(counter.isImports(possibleImport));

	}

	@Test
	public void testIsClass() {
		String possibleClass = "   public class jUaNFER_NaNDO      {";
		assertTrue(counter.isClass(possibleClass));

	}

	@Test
	public void testIsMethod() {
		String possibleMethod = "public File getReadedFile(){";
		assertTrue(counter.isMethod(possibleMethod));

	}

	@Test
	public void testGetAllMethods() {
		String possibleMethod = "public boolean isPackage(String possiblePackage){Pattern packageExpression= Pattern.compile(packagePattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isImports(String possiblePackage){Pattern packageExpression= Pattern.compile(importsPattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isClass(String possibleClass){Pattern packageExpression= Pattern.compile(classPattern);Matcher matcher = packageExpression.matcher(possibleClass);return matcher.matches();}public boolean isMethod(String possibleMethod){Pattern packageExpression= Pattern.compile(methodPAttern);Matcher matcher = packageExpression.matcher(possibleMethod);return matcher.matches();}public void returnMethods(String posibleClass){Pattern packageExpression= Pattern.compile(methodPAttern);Matcher matcher = packageExpression.matcher(posibleClass);while (matcher.find()) {System.out.println(matcher.group());}}";
		List<MethodComponent> methods = counter.returnMethods(possibleMethod);
		assertEquals(5, methods.size());

	}

	@Test
	public void testGetAllClasses() {
		String possibleClass = "package co.edu.udea.ps.psp.p2.countermodel;import java.util.ArrayList;import java.util.List;import java.util.regex.Matcher;import java.util.regex.Pattern;public class CounterModel {private static final String packagePattern=\"([\\s*|\\n*]*)package\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;\";private static final String importsPattern=\"(([\\s*|\\n*]*)import\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;)*\";private static final String classPattern=\"([\\s*|\\n*]*)(public|private|)([\\s*|\\n*]+)\"//Es el accesor de la clase+ \"(class|interface)([\\s*|\\n*]+)(\\w+)\"//tipo y nombre de clase+ \"(([\\s*|\\n*]+)(implements([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|)\" //si implementa o no clase+ \"((([\\s*|\\n*]+)(extends([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|))\"//Si extiende o no una clase+ \"([\\s*|\\n*]+)(\\{)\";//termina con llaves que abren y cierranprivate static final String methodPAttern=\"([\\s*|\\n*]*)(((public|private)([\\s*|\\n*]+))|)\"//Modificadores+ \"((static)([\\s*|\\n*]*))*((final)([\\s*|\\n*]*))*\"//si es final o static+ \"(((void)([\\s+|\\n+]*))|\"//retorno de void+ \"((\\w+)((\\<\\w+>([\\s*|\\n*]*))|([\\s+|\\n+]+))))\" //tipo de retorno+ \"(\\w+)\" //Nombre del metodo+ \"(\\()((((\\w+)\\s+(\\w+))((,)(\\w+)\\s+(\\w+))*)*)(\\))\"//parametros+ \"(\\s*)(\\{)\"//termina con abre ;public boolean isPackage(String possiblePackage){Pattern packageExpression= Pattern.compile(packagePattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isImports(String possiblePackage){Pattern packageExpression= Pattern.compile(importsPattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isClass(String possibleClass){Pattern packageExpression= Pattern.compile(classPattern);Matcher matcher = packageExpression.matcher(possibleClass);return matcher.matches();}public boolean isMethod(String possibleMethod){Pattern packageExpression= Pattern.compile(methodPAttern);Matcher matcher = packageExpression.matcher(possibleMethod);return matcher.matches();}public List<String> returnMethods(String posibleClass){Pattern methodExpression= Pattern.compile(methodPAttern);List<String>methods= new ArrayList<>();Matcher matcher = methodExpression.matcher(posibleClass);while (matcher.find()) {methods.add(matcher.group());}return methods;}public List<String> returnClasses(String posibleClass){Pattern classExpression= Pattern.compile(classPattern);List<String>classes= new ArrayList<>();Matcher matcher = classExpression.matcher(posibleClass);while (matcher.find()) {classes.add(matcher.group());}return classes;}}";
		ClassComponent classComponent = counter.returnClass(possibleClass);
		assertTrue(classComponent.getNameOfClass().contains("CounterModel"));

	}

	@Test
	public void testCountLineOfCode() {
		String possibleMethod = "package co.edu.udea.ps.psp.p2.countermodel;import java.util.ArrayList;import java.util.List;import java.util.regex.Matcher;import java.util.regex.Pattern;public class CounterModel {private static final String packagePattern=\"([\\s*|\\n*]*)package\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;\";private static final String importsPattern=\"(([\\s*|\\n*]*)import\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;)*\";private static final String classPattern=\"([\\s*|\\n*]*)(public|private|)([\\s*|\\n*]+)\"//Es el accesor de la clase+ \"(class|interface)([\\s*|\\n*]+)(\\w+)\"//tipo y nombre de clase+ \"(([\\s*|\\n*]+)(implements([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|)\" //si implementa o no clase+ \"((([\\s*|\\n*]+)(extends([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|))\"//Si extiende o no una clase+ \"([\\s*|\\n*]+)(\\{)\";//termina con llaves que abren y cierranprivate static final String methodPAttern=\"([\\s*|\\n*]*)(((public|private)([\\s*|\\n*]+))|)\"//Modificadores+ \"((static)([\\s*|\\n*]*))*((final)([\\s*|\\n*]*))*\"//si es final o static+ \"(((void)([\\s+|\\n+]*))|\"//retorno de void+ \"((\\w+)((\\<\\w+>([\\s*|\\n*]*))|([\\s+|\\n+]+))))\" //tipo de retorno+ \"(\\w+)\" //Nombre del metodo+ \"(\\()((((\\w+)\\s+(\\w+))((,)(\\w+)\\s+(\\w+))*)*)(\\))\"//parametros+ \"(\\s*)(\\{)\"//termina con abre ;public boolean isPackage(String possiblePackage){Pattern packageExpression= Pattern.compile(packagePattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isImports(String possiblePackage){Pattern packageExpression= Pattern.compile(importsPattern);Matcher matcher = packageExpression.matcher(possiblePackage);return matcher.matches();}public boolean isClass(String possibleClass){Pattern packageExpression= Pattern.compile(classPattern);Matcher matcher = packageExpression.matcher(possibleClass);return matcher.matches();}public boolean isMethod(String possibleMethod){Pattern packageExpression= Pattern.compile(methodPAttern);Matcher matcher = packageExpression.matcher(possibleMethod);return matcher.matches();}public List<String> returnMethods(String posibleClass){Pattern methodExpression= Pattern.compile(methodPAttern);List<String>methods= new ArrayList<>();Matcher matcher = methodExpression.matcher(posibleClass);while (matcher.find()) {methods.add(matcher.group());}return methods;}public List<String> returnClasses(String posibleClass){Pattern classExpression= Pattern.compile(classPattern);List<String>classes= new ArrayList<>();Matcher matcher = classExpression.matcher(posibleClass);while (matcher.find()) {classes.add(matcher.group());}return classes;}}";
		int lineOfCode = counter.countLinesOfCode(possibleMethod);
		assertEquals(38, lineOfCode);

	}

}
