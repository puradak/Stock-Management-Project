package exceptions;

public class NotInRangeException extends Exception {
	private static final long serialVersionUID = 1989957456272811434L;
	
	private int number;
	public NotInRangeException(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
}
