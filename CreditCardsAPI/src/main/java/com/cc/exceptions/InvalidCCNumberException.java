/**
 * 
 */
package com.cc.exceptions;

/**
 * @author Administrator
 *
 */
public class InvalidCCNumberException extends RuntimeException{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	public InvalidCCNumberException(String msg) {
		this.setErrorMsg(msg);
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
