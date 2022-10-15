package org.alkemy.challengedisney.exception;

public class UnauthorizedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1917659249316287986L;

    public UnauthorizedException(String detail) {
        super(detail);
    }

}
