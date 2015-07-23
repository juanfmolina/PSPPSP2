package co.edu.udea.ps.psp.p2.countermodel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounterModel {
	
	private static final String packagePattern="([\\s*|\\n*]*)package\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;";
	private static final String importsPattern="(([\\s*|\\n*]*)import\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;)*";
	private static final String classPattern="([\\s*|\\n*]*)(public|private|)([\\s*|\\n*]+)"//Es el accesor de la clase
			+ "(class|interface)([\\s*|\\n*]+)(\\w+)"//tipo y nombre de clase
			+ "(([\\s*|\\n*]+)(implements([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|)" //si implementa o no clase
			+ "((([\\s*|\\n*]+)(extends([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|))"//Si extiende o no una clase
			+ "([\\s*|\\n*]+)(\\{.*\\})";//termina con llaves que abren y cierran
	
	private static final String methodPAttern="([\\s*|\\n*]*)(((public|private)([\\s*|\\n*]+))|)"//Modificadores
			+ "((static)([\\s*|\\n*]*))*((final)([\\s*|\\n*]*))*"//si es final o static
			+ "(((void)([\\s+|\\n+]*))|"//retorno de void
			+ "((\\w+)((\\<\\w+>([\\s*|\\n*]*))|([\\s+|\\n+]+))))" //tipo de retorno
			+ "(\\w+)" //Nombre del metodo
			+ "(\\((((.+)\\s+(.+)(,(.+)\\s+(.+))*))*\\))"//parametros
			+ "(\\s+)(\\{.*\\})"//termina con abre y cierra llaves
			;
	
	public boolean isPackage(String possiblePackage){
		Pattern packageExpression= Pattern.compile(packagePattern);
		Matcher matcher = packageExpression.matcher(possiblePackage);
		return matcher.matches();
		
	}
	
	public boolean isImports(String possiblePackage){
		Pattern packageExpression= Pattern.compile(importsPattern);
		Matcher matcher = packageExpression.matcher(possiblePackage);
		return matcher.matches();
		
	}
	
	public boolean isClass(String possibleClass){
		Pattern packageExpression= Pattern.compile(classPattern);
		Matcher matcher = packageExpression.matcher(possibleClass);
		return matcher.matches();
		
	}
	
	public boolean isMethod(String possibleMethod){
		Pattern packageExpression= Pattern.compile(methodPAttern);
		Matcher matcher = packageExpression.matcher(possibleMethod);
		return matcher.matches();
		
	}
}
