/**
 *
 */
package org.pako.egen.weight.web.bean;

/**
 * This bean is used to map the time range to be used in the retrieval of the records
 *
 * @author Pako Castillo
 *
 */
public class IncomingRangeBean {

	private Long initialTimeStamp;

	private Long endingTimeStamp;

	/**
	 * @return the initialTimeStamp
	 */
	public Long getInitialTimeStamp() {
		return initialTimeStamp;
	}

	/**
	 * @param initialTimeStamp the initialTimeStamp to set
	 */
	public void setInitialTimeStamp(Long initialTimeStamp) {
		this.initialTimeStamp = initialTimeStamp;
	}

	/**
	 * @return the endingTimeStamp
	 */
	public Long getEndingTimeStamp() {
		return endingTimeStamp;
	}

	/**
	 * @param endingTimeStamp the endingTimeStamp to set
	 */
	public void setEndingTimeStamp(Long endingTimeStamp) {
		this.endingTimeStamp = endingTimeStamp;
	}
}
