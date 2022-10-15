package org.alkemy.challengedisney.vo;

import java.io.Serializable;

public class ResponseRequestVO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6856252483585639593L;
	private final String jwttoken;

	public ResponseRequestVO(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}