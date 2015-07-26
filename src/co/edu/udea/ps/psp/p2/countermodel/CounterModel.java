package co.edu.udea.ps.psp.p2.countermodel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.udea.ps.psp.p2.model.ClassComponent;
import co.edu.udea.ps.psp.p2.model.MethodComponent;

public class CounterModel {
	
	private static final String packagePattern="([\\s*|\\n*]*)package\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;";
	private static final String importsPattern="(([\\s*|\\n*]*)import\\s+([a-zA-Z0-9]+|[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)+;)*";
	private static final String classPattern="([\\s*|\\n*]*)(public|private|)([\\s*|\\n*]+)"//Es el accesor de la clase
			+ "(class|interface)([\\s*|\\n*]+)(\\w+)"//tipo y nombre de clase
			+ "(([\\s*|\\n*]+)(implements([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|)" //si implementa o no clase
			+ "((([\\s*|\\n*]+)(extends([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,\\w+)*)|))"//Si extiende o no una clase
			+ "([\\s*|\\n*]+)(\\{)";//termina con llaves que abren y cierran

	private static final String methodPAttern="([\\s*|\\n*]*)(((public|private|protected)([\\s*|\\n*]+))|)"//Modificadores
			+ "((static)([\\s*|\\n*]*))*((final)([\\s*|\\n*]*))*"//si es final o static
			+ "(((void)([\\s+|\\n+]*))|"//retorno de void
			+ "((\\w+)((\\<\\w+>([\\s*|\\n*]*))|([\\s+|\\n+]+))))" //tipo de retorno
			+ "(\\w+)" //Nombre del metodo
			+ "(\\()(((((\\w+)((\\<\\w+>([\\s*|\\n*]*))|)\\s+(\\w+)))(,\\s*\\w+((\\<\\w+>([\\s*|\\n*]*))|)\\s+\\w+)*)*)(\\))"//parametros
			+ "(\\s*)((throws([\\s*|\\n*]+)(\\w+)([\\s*|\\n*]*)(,([\\s*|\\n*]*)\\w+)*)|)"//Si lanza o no Excepciones
			+ "(\\{)"//termina con abre 
			;
	
	private static final String parametersPattern=
			"(\\()(((((\\w+)((\\<\\w+>([\\s*|\\n*]*))|)\\s+(\\w+)))(,\\s*\\w+((\\<\\w+>([\\s*|\\n*]*))|)\\s+\\w+)*)*)(\\))";
	
	public boolean areParameters(String posibleParameters){
		Pattern packageExpression= Pattern.compile(parametersPattern);
		Matcher matcher = packageExpression.matcher(posibleParameters);
		return matcher.matches();
		
	}
	
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
	
	public List<MethodComponent> returnMethods(String posibleClass){
		Pattern methodExpression= Pattern.compile(methodPAttern);
		List<MethodComponent>methods= new ArrayList<>();
		Matcher matcher = methodExpression.matcher(posibleClass);
		
		while (matcher.find()) {
			MethodComponent component= new MethodComponent( matcher.start(),
					matcher.end(),
					matcher.group().replace("\t", ""));
			methods.add(component);
		}
		return methods;
		
	}
	
	public ClassComponent returnClass(String posibleClass){
		Pattern classExpression= Pattern.compile(classPattern);
		ClassComponent classComponent = null;
		Matcher matcher = classExpression.matcher(posibleClass);
		
		if (matcher.find()) {
			classComponent = new ClassComponent(matcher.group().replace("\t", ""),
					matcher.start(), matcher.end());
		}
		return classComponent;
		
	}
	
	public int countLinesOfCode(String component){
		String [] keyWords= {";",":", "if", "while", "for", "try", "catch"};
		int totalLinesOfCode=0;
		for (int i = 0; i < keyWords.length; i++) {
			totalLinesOfCode+=(component.length() - component.replace(keyWords[i], "").length()) / keyWords[i].length();
		}		
		return totalLinesOfCode;
	}
}
