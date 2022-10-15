package org.alkemy.challengedisney.exception;

public class ForbiddenException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3922038424708407915L;

	public ForbiddenException(String detail) {
        super(detail);
    }

}
