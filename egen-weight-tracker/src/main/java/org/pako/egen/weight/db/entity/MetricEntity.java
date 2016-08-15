/**
 *
 */
package org.pako.egen.weight.db.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * This entity represents a captured metric received from the sensor
 *
 * @author Pako Castillo
 *
 */
@Entity
public class MetricEntity extends BaseEntity {

	/** Initial weight **/
	private Integer baseWeight;

	/** Increased weight extracted using the base weight **/
	private Integer calculatedWeight;

	/** Time when the measurement was done **/
	private Long measurementTime;

	/** True if the measurement falls into the threshold **/
	private String validMetric;

	/** If the metric is invalid, populate the description **/
	private String invalidDescription;

	/**
	 * @return the baseWeight
	 */
	public Integer getBaseWeight() {
		return baseWeight;
	}

	/**
	 * @param baseWeight the baseWeight to set
	 */
	public void setBaseWeight(Integer baseWeight) {
		this.baseWeight = baseWeight;
	}

	/**
	 * @return the calculatedWeight
	 */
	public Integer getCalculatedWeight() {
		return calculatedWeight;
	}

	/**
	 * @param calculatedWeight the calculatedWeight to set
	 */
	public void setCalculatedWeight(Integer calculatedWeight) {
		this.calculatedWeight = calculatedWeight;
	}

	/**
	 * @return the measurementTime
	 */
	public Long getMeasurementTime() {
		return measurementTime;
	}

	/**
	 * @param measurementTime the measurementTime to set
	 */
	public void setMeasurementTime(Long measurementTime) {
		this.measurementTime = measurementTime;
	}

	/**
	 * @return the isValidMetric
	 */
	public String getValidMetric() {
		return validMetric;
	}

	/**
	 * @param isValidMetric the isValidMetric to set
	 */
	public void setValidMetric(String validMetric) {
		this.validMetric = validMetric;
	}

	/**
	 * @return the invalidDescription
	 */
	public String getInvalidDescription() {
		return invalidDescription;
	}

	/**
	 * @param invalidDescription the invalidDescription to set
	 */
	public void setInvalidDescription(String invalidDescription) {
		this.invalidDescription = invalidDescription;
	}
}
