package co.edu.udea.ps.psp.p2.countermodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

import co.edu.udea.ps.psp.p2.exception.MathModelException;

public class MathCalculator {

	public double calculateMean(LinkedList<Double> numbers) throws MathModelException{
		if (!this.validateListSize(numbers)) {
			throw new MathModelException("La lista de números está vacía");
		}
		double sum=0;
		double count=0;
		for (Double number : numbers) {
			sum+=number;
			count+=1;
		}
		
		return setscale(4,sum/count);
	}
	
	private double setscale(int scale, double number) {
		BigDecimal bd = new BigDecimal(number);
	    bd = bd.setScale(scale, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public boolean validateListSize(LinkedList<Double> numbers){
		if (numbers==null || numbers.isEmpty()) {
			return false;
		}
		return true;
	}

	public double calculateStandarDesviation(LinkedList<Double> numbers) throws MathModelException{
		if (!this.validateListSize(numbers)) {
			throw new MathModelException("La lista de números está vacía");
		}
		
		double mean = this.calculateMean(numbers);
		int numbercount=numbers.size();
		double sumOfSqrt = 0;
		
		for (Double number : numbers) {
			sumOfSqrt+= (number-mean)*(number-mean);
		}
		
		double sd = Math.sqrt((sumOfSqrt / (numbercount - 1)));
		
		return setscale(4, sd);
	}

}
