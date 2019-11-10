package com.api.sample.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Return {
	@JsonProperty("status")
	public String status;
	@JsonProperty("message")
	public String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
