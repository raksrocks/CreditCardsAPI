/**
 * 
 */
package com.cc.utils;

import java.util.stream.Stream;

import com.cc.exceptions.InvalidCCNumberException;
import com.cc.exceptions.RequestFormatException;
import com.cc.model.CreditCard;

/**
 * @author Administrator
 *
 */
public class ProjectUtils {
	/**
	 * @param number
	 * @return
	 */
	public static boolean validateCardNumber(String number) {
		if(null == number || number.length()==0)
			return false;
		/*
		 * Drop the last digit from the number. The last digit is what we want to check
		 * against Reverse the numbers Multiply the digits in odd positions (1, 3, 5,
		 * etc.) by 2 and subtract 9 to all any result higher than 9 Add all the numbers
		 * together The check digit (the last number of the card) is the amount that you
		 * would need to add to get a multiple of 10 (Modulo 10)
		 */
		
		int sum = 0;
		int length = number.length();
		// convert string to in array
		int[] nums = Stream.of(number.split("")).mapToInt(Integer::parseInt).toArray();

		
		for(int i=0;i<nums.length;i++) {
			// get digits in reverse
			int num = nums[length - i -1];
			
			//multiply with 2 on every other number
			
			if(i%2 == 1)
				num = num *2;
			// if more than 9 round it back to 0
			sum += num > 9? num-9:num;
		}
		
		//validate checksum
		return sum % 10 == 0;
		
	}

	/**
	 * @param card
	 * @return
	 */
	public static boolean isValidInput(CreditCard card) {
		
		//Validate Name
		if(null==card.getName()||card.getName().trim().isEmpty())
			return false;
		//validate Number
		//if(null==card.getNumber()||card.getNumber().isEmpty()||card.getNumber().get)
			//return false;		
		//Validate Limit
				
		//Validate Balance
		
		return false;
	}

	/**
	 * @param card
	 */
	public static void validateInput(CreditCard card) {
		
		// If input is either empty, null or contains numbers
		if(null==card.getName() || card.getName().trim().isEmpty()||card.getName().matches(".*\\d.*"))
			throw new RequestFormatException("Input value for 'name' is not valid");
		
		//If input is either empty, null or contains text and more than 19 characters
		if(null==card.getNumber() || card.getNumber().trim().isEmpty()|| !card.getNumber().matches("[0-9]+") || card.getNumber().length()>19)
			throw new RequestFormatException("Input value for 'number' is not valid");

		// IF limit is negative
		if(null==card.getLimit() || card.getLimit()<0)
			throw new RequestFormatException("Input value for 'limit' is not valid");
		
		// IF Balance is not zero
		if(null==card.getBalance() || card.getBalance()!=0)
			throw new RequestFormatException("Input value for 'Balance' is not valid");
				
		if(!validateCardNumber(card.getNumber()))
			throw new InvalidCCNumberException("Input Credit Card number '"+card.getNumber()+"' is not valid as per Luhn10 rules");
	}
	
}
