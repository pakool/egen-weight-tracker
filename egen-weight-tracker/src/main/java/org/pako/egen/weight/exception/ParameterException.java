/**
 *
 */
package org.pako.egen.weight.exception;

/**
 * Main exception thrown when the specified parameters on different methods are not correct
 *
 * @author Pako Castillo
 *
 */
public class ParameterException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -6296975746624756926L;

	/**
	 * Constructor passing the exception message to the parent
	 *
	 * @param e
	 */
	public ParameterException(String e){
		super(e);
	}

}
