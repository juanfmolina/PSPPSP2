package co.edu.udea.ps.psp.p2.model;

public class MethodComponent {
	private int startsIn;
	private int endsIn;
	private String nameOfComponent;
	private int numberOfLinesOfCode;
	
	
	
	public int getNumberOfLinesOfCode() {
		return numberOfLinesOfCode;
	}
	public void setNumberOfLinesOfCode(int numberOfLinesOfCode) {
		this.numberOfLinesOfCode = numberOfLinesOfCode;
	}
	public MethodComponent(int startsIn, int endsIn, String nameOfComponent) {
		super();
		this.startsIn = startsIn;
		this.endsIn = endsIn;
		this.nameOfComponent = nameOfComponent;
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
	public String getNameOfComponent() {
		return nameOfComponent;
	}
	public void setNameOfComponent(String nameOfComponent) {
		this.nameOfComponent = nameOfComponent;
	}
	
}
