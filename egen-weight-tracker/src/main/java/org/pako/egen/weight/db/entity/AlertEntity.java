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
	private String altertMessage;

	/**
	 * @return the altertMessage
	 */
	public String getAltertMessage() {
		return altertMessage;
	}

	/**
	 * @param altertMessage the altertMessage to set
	 */
	public void setAltertMessage(String altertMessage) {
		this.altertMessage = altertMessage;
	}

}
