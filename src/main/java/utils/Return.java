package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Return {
	@JsonProperty("status")
	public int status;
	@JsonProperty("message")
	public String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
