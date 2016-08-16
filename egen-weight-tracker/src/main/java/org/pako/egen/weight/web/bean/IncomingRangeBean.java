/**
 *
 */
package org.pako.egen.weight.web.bean;

import java.io.Serializable;

import org.pako.egen.weight.util.StringUtils;

/**
 * This bean is used to map the time range to be used in the retrieval of the records
 *
 * @author Pako Castillo
 *
 */
public class IncomingRangeBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3224619003028527468L;

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

	@Override
	public String toString(){
		return StringUtils.reflectObject(this);
	}
}
