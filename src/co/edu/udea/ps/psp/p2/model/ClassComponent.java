package co.edu.udea.ps.psp.p2.model;

import java.util.List;

public class ClassComponent {
	
	private List<MethodComponent> methods;
	
	private String nameOfClass;
	
	private int startsIn;
	
	private int endsIn;
	
	private int numberOfLinesOfCode;

	public ClassComponent(String nameOfClass, int startsIn, int endsIn) {
		super();
		this.nameOfClass = nameOfClass;
		this.startsIn = startsIn;
		this.endsIn = endsIn;
	}

	public List<MethodComponent> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodComponent> methods) {
		this.methods = methods;
	}

	public String getNameOfClass() {
		return nameOfClass;
	}

	public void setNameOfClass(String nameOfClass) {
		this.nameOfClass = nameOfClass;
	}

	public int getStartsIn() {
		return startsIn;
	}

	public void setStartsIn(int startsIn) {
		this.startsIn = startsIn;
	}

	public int getEndsIn() {
		return endsIn;
	}

	public void setEndsIn(int endsIn) {
		this.endsIn = endsIn;
	}

	public int getNumberOfLinesOfCode() {
		return numberOfLinesOfCode;
	}

	public void setNumberOfLinesOfCode(int numberOfLinesOfCode) {
		this.numberOfLinesOfCode = numberOfLinesOfCode;
	}
	
	
}
