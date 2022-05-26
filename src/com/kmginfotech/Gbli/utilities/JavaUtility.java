package com.kmginfotech.Gbli.utilities;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JavaUtility {

	/**
	 * This method is used to generate a random number between the two provided
	 * values.
	 * 
	 * @param lowerBound Lower limit of the generated random number.
	 * @param upperBound Upper limit of the generated random number.
	 * @return Return the random number generated between the parameters i.e.
	 *         lowerBound and upperBound(lowerBound(Inclusive) and
	 *         upperBound(Exclusive)).
	 * 
	 */
	public int getRandomNumber(int lowerBound, int upperBound) {

		Random random = new Random();

		return (random.nextInt(upperBound - lowerBound) + lowerBound);

	}

	/**
	 * This method is used to split a String based on a separator present in
	 * between.
	 * 
	 * @param str           The string which needs to be split.
	 * @param separatorChar The separator, on the basis of which the string will
	 *                      split.
	 * @return It will return a String array, which contains the split strings.
	 * 
	 */
	public String[] split(String str, String separatorChar) {

		return (str.split(separatorChar));

	}

	/**
	 * This method is used to convert an Integer array into a List.
	 * 
	 * @param arr Accept an Integer array which needs to be converted into List.
	 * @return It will return a List containing all the elements of the passed
	 *         integer array.
	 * 
	 */
	public List<Integer> convertAnIntegerArrayIntoList(int[] arr) {

		return (Arrays.stream(arr).boxed().collect(Collectors.toList()));

	}

	/**
	 * This method is used to convert an String array into a List.
	 * 
	 * @param arr Accept a String array which needs to be converted into List.
	 * @return It will return a List containing all the elements of the passed
	 *         String array.
	 * 
	 */
	public List<String> convertAnStringArrayIntoList(String[] arr) {

		return (Arrays.asList(arr));

	}

	/**
	 * This function will pause the current thread by specified time in seconds.
	 * 
	 * @param timeOutInSeconds time in seconds.
	 * @return Does not return any value.
	 * 
	 */
	public void holdOn(int timeOutInSeconds) {
		try {
			long time = (long) 1000 * timeOutInSeconds;
			Thread.sleep(time);
		} catch (Exception e) {

		}
	}

	/**
	 * This function will return the current system date and time.
	 * 
	 * @return Return the current system date and time.
	 * 
	 */
	public String getCurrentTimeStamp() {

		SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy HH_mm_ss");

		Date date = new Date();

		return formatter.format(date);
	}

	/**
	 * This method is used to get the Sub String from the Passed String value.
	 * 
	 * @param value      String from which the sub string is extracted.
	 * @param beginIndex Starting index from which the sub string is extracted.
	 * @return Return the sub string
	 */

	public String getSubString(String value, int beginIndex) {

		return value.substring(beginIndex).trim();

	}

}
