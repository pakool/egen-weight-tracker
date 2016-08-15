/**
 *
 */
package org.pako.egen.weight.web.bean;

import java.io.Serializable;

/**
 * This represents the incoming request from the sensor
 *
 * @author Pako Castillo
 *
 */
public class IncomingMetricBean implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4442324800641269357L;

	/** Metric date **/
	private Long timeStamp;

	/**
	 * @return the timeStamp
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/** Current metric value **/
	private Integer value;

}
