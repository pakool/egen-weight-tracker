/**
 *
 */
package org.pako.egen.weight.db.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * @author Pako Castillo
 *
 */
@Entity
public class AlertEntity extends ReportingEntity {

	/** Message of the alert **/
	private String alertMessage;

	/**
	 * @return the alertMessage
	 */
	public String getAlertMessage() {
		return alertMessage;
	}

	/**
	 * @param alertMessage the alertMessage to set
	 */
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

}
