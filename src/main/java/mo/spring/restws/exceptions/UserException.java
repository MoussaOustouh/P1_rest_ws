package mo.spring.restws.exceptions;

public class UserException extends RuntimeException{
	private static final long serialVersionUID = -6434713780339848756L;

	public UserException(String message) {
		super(message); 
	}
}
