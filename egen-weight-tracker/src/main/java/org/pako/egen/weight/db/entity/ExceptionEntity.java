/**
 *
 */
package org.pako.egen.weight.db.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * This entity represents all the major exceptions log in the DB
 *
 * @author Pako Castillo
 *
 */
@Entity
public class ExceptionEntity extends ReportingEntity{

	private String errorMessage;

	private String stackTrace;

	private String errorLevel;

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace the stackTrace to set
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the errorLevel
	 */
	public String getErrorLevel() {
		return errorLevel;
	}

	/**
	 * @param errorLevel the errorLevel to set
	 */
	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}
}
