package org.alkemy.challengedisney.vo;

import org.springframework.http.HttpStatus;

public class ResponseCreatedVO {
	private Integer id;
	private HttpStatus status;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
