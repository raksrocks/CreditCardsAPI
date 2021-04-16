/**
 * 
 */
package com.cc.utils;

/**
 * @author Administrator
 *
 */
public class ProjectUtils {
	public static boolean validateCardNumber(String number) {
		if(number.startsWith("4"))
			return false;
		else 
			return true;
	}
}
