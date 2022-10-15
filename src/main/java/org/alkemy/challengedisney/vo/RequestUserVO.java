package org.alkemy.challengedisney.vo;

import java.io.Serializable;

public class RequestUserVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2785900954053998543L;
	private String username;
	private String password;
	
	//need default constructor for JSON Parsing
	public RequestUserVO()
	{
		
	}

	public RequestUserVO(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}