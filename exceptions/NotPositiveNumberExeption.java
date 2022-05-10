package exceptions;

public class NotPositiveNumberExeption extends Exception {
	private static final long serialVersionUID = 3806896293181357422L;
	private int number;
	private double real;
	
	public NotPositiveNumberExeption(int number) {
		this.number = number;
		this.real = 1.0;
	}
	
	public NotPositiveNumberExeption(double real) {
		this.number = 1;
		this.real = real;
	}

	public int getNumber() {
		return number;
	}

	public double getReal() {
		return real;
	}
	
}
