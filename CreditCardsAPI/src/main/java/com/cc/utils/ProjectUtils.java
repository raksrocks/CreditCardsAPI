/**
 * 
 */
package com.cc.utils;

import java.util.stream.Stream;

/**
 * @author Administrator
 *
 */
public class ProjectUtils {
	public static boolean validateCardNumber(String number) {
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
}
