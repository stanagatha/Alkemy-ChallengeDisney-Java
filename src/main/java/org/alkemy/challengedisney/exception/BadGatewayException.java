package org.alkemy.challengedisney.exception;

public class BadGatewayException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2410283669268271989L;

    public BadGatewayException(String detail) {
        super(detail);
    }

}
