/**
 * 
 */
package com.cc.exceptions;

/**
 * @author Administrator
 *	This Exception is thrown when a credit card number is not as per Luhn10 rules
 */
public class InvalidCCNumberException extends RequestFormatException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	
	/**
	 * @param msg
	 */
	public InvalidCCNumberException(String msg) {
		super();
		this.setErrorMsg(msg);
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
