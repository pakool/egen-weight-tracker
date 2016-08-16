/**
 *
 */
package org.pako.egen.weight.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * Different set of tools to handle strings
 *
 * @author Pako Castillo
 *
 */
public class StringUtils {

	/**
	 * Return true if the string has a value
	 *
	 * @param s
	 * @return
	 */
	public static boolean hasValue(String s){
		return s != null && s.trim().length() > 0;
	}


	/**
	 * Reflect the specified object and build a string with all it's getters and their values
	 *
	 * @param o
	 * @return
	 */
	public static String reflectObject(Object o){
		StringBuilder sb = new StringBuilder();

		for (java.lang.reflect.Method m : o.getClass().getMethods()) {
			try {
				if (m.getName().contains("get")) {
					sb.append(m.getName().substring(3)).append(": ").append(m.invoke(o)).append(" ");
				} else if ( m.getName().contains("is")) {
					sb.append(m.getName().substring(2)).append(": ").append(m.invoke(o)).append(" ");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				//				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * Return the provided exception as a String including the stacktrace
	 *
	 * @param e
	 * @return
	 */
	public static String transformException(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		e.printStackTrace(ps);

		return baos.toString();
	}

	/**
	 * Return the current date minus the number of specified days
	 *
	 * @param days
	 * @return
	 */
	public static Date getDateMinusDays(Integer days){
		if(days != null){
			int total = days > 0 ? days * -1 : days;
			Calendar cal = Calendar.getInstance();
			cal.add( Calendar.DAY_OF_YEAR, total);

			return cal.getTime();
		}

		return null;
	}

	/**
	 * Return a sql timestamp minus for the current day minus the specified days
	 *
	 * @param days
	 * @return
	 */
	public static Timestamp getTimestampMinusDays(Integer days){
		Date d = getDateMinusDays(days);
		if(d != null){
			return new Timestamp(d.getTime());
		}

		return null;
	}

	/**
	 * Return true if the specified string is a number
	 *
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s){
		return s != null && s.matches("[0-9]+");
	}

	/**
	 * Return a base64 encoded String
	 *
	 * @param s
	 * @return
	 */
	public static String getBase64Encode(String s){
		return Base64.getEncoder().encodeToString(s.getBytes());
	}

	/**
	 * Decode the provided string
	 *
	 * @param s
	 * @return
	 */
	public static String getBase64Decode(String s){
		return new String(Base64.getDecoder().decode(s));
	}
}
