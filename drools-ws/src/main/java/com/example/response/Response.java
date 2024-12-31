package com.example.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Response {
	
	private String response;
	
	public Response(String response) {
		this.response = response;
	}

	@JsonGetter("response")
	public String getResponse() {
		return response;
	}

	@JsonSetter("response")
	public void setResponse(String response) {
		this.response = response;
	}

}
