package com.tigerj.web;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class RestResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 返回码
	private HttpStatus code = HttpStatus.OK;
	
	// 返回内容
	private T responseParams = null;
	
	public RestResponse() {
	}
	
	public RestResponse(T responseParams) {
		this.responseParams = responseParams;
	}
	
	public RestResponse(HttpStatus code,T responseParams){
		this.code = code;
		this.responseParams = responseParams;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public T getResponseParams() {
		return responseParams;
	}

	public void setResponseParams(T responseParams) {
		this.responseParams = responseParams;
	}

}
