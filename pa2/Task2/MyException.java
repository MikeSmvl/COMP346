package common;
/**
 * Implements the MyException class
 * This exception class handles errors
 * @author Mikael Samvelian
 *
 */
public class MyException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MyException(String message)
	{
		super(message);
	}
}
