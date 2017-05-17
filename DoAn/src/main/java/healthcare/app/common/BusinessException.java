package healthcare.app.common;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * Constructor
     *
     * @param messageId Message ID
     */
	public BusinessException(final String message) {
	    super(message);
	  }

	public BusinessException(final String message, final Throwable cause) {
	    super(message, cause);
	  }

	  /**
	   * Construct an Exception with message.
	   *
	   * @param message
	   *     The description of the exception.
	   * @return An instance of the Exception.
	   */
	  public static BusinessException getInstance(final String message) {
	    return new BusinessException(message);
	  }

	  /**
	   * Construct an Exception with message and cause.
	   *
	   * @param message
	   *     The description of the exception.
	   * @param cause
	   *     The cause of the exception.
	   * @return An instance of the Exception.
	   */
	  public static BusinessException getInstance(final String message, final Throwable cause) {
	    return new BusinessException(message, cause);
	  }
}
