/**
 *
 */
package org.pako.egen.weight.db.entity;

/**
 * Represents the global information that needs to be contained in a reporting implementation
 *
 * @author Pako Castillo
 *
 */
public abstract class ReportingEntity extends BaseEntity {

	/** Which class is reporting an action **/
	private String reportingClass;

	/** Type of reporting element **/
	private String reportingType;

	/** Time when the report was raised **/
	private Long reportingTime;

	/**
	 * @return the reportingClass
	 */
	public String getReportingClass() {
		return reportingClass;
	}

	/**
	 * @param reportingClass the reportingClass to set
	 */
	public void setReportingClass(String reportingClass) {
		this.reportingClass = reportingClass;
	}

	/**
	 * @return the reportingType
	 */
	public String getReportingType() {
		return reportingType;
	}

	/**
	 * @param reportingType the reportingType to set
	 */
	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}

	/**
	 * @return the reportingTime
	 */
	public Long getReportingTime() {
		return reportingTime;
	}

	/**
	 * @param reportingTime the reportingTime to set
	 */
	public void setReportingTime(Long reportingTime) {
		this.reportingTime = reportingTime;
	}
}
