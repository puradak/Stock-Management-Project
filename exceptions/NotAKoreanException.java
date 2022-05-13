package exceptions;

public class NotAKoreanException extends Exception{
	private static final long serialVersionUID = 4922588851128015054L;

	private String value;
	public NotAKoreanException(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
