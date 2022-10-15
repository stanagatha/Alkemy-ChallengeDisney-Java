package org.alkemy.challengedisney.vo;

public class ErrorMessageVO {

    private String error;
    private String message;
    private Integer code;

    public ErrorMessageVO() {
    }
    
    public ErrorMessageVO(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = code;
    }

	@Override
	public String toString() {
		return "{\"error\":" + error + ", \"message\":" + message + ", \"code\":" + code + "}";
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

    
}