/**
 * 
 */
package com.cc.exceptions;

/**
 * @author Administrator
 *
 */
public class RequestFormatException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	public RequestFormatException(String msg) {
		this.setErrorMsg(msg);
	}
	public RequestFormatException() {
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
