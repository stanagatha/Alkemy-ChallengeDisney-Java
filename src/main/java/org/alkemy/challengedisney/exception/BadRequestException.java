package org.alkemy.challengedisney.exception;

public class BadRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5885465905950688585L;

    public BadRequestException(String detail) {
        super(detail);
    }

}
