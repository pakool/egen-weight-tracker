/**
 *
 */
package org.pako.egen.weight.db.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;

/**
 * @author Pako Castillo
 *
 */
@Entity
public class AlertEntity extends ReportingEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7972688363582210795L;

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
