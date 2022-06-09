package exceptions;

public class IntegerNotInRangeException extends Exception{
	private static final long serialVersionUID = -3380879946534949187L;
	private int from;
	private int to;
	
	public IntegerNotInRangeException(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return this.from;
	}
	
	public int getTo() {
		return this.to;
	}
	
}
