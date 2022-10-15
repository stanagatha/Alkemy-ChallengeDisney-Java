package org.alkemy.challengedisney.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CustomUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4457920388556119617L;
	@JsonAlias(value = {"userName"})
	private String userName;
	@JsonAlias(value = {"passWord"})
	private String passWord;
	private String email;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public CustomUserDTO() {
		super();
	}

	public CustomUserDTO(String userName, String passWord, String email) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
