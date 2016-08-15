/**
 *
 */
package org.pako.egen.weight.db.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * Audit entity containing information of when was the system accessed to retrieve the information
 *
 * @author Pako Castillo
 *
 */
@Entity
public class ReadAuditEntity extends BaseEntity {

	/** Name of the user accessing the records in the system **/
	private String accessUser;

	/**
	 * @return the accessUser
	 */
	public String getAccessUser() {
		return accessUser;
	}

	/**
	 * @param accessUser the accessUser to set
	 */
	public void setAccessUser(String accessUser) {
		this.accessUser = accessUser;
	}

	/**
	 * @return the transactionName
	 */
	public String getTransactionName() {
		return transactionName;
	}

	/**
	 * @param transactionName the transactionName to set
	 */
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	/**
	 * @return the transactionDescription
	 */
	public String getTransactionDescription() {
		return transactionDescription;
	}

	/**
	 * @param transactionDescription the transactionDescription to set
	 */
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	/**
	 * @return the retrievedRecordCount
	 */
	public Integer getRetrievedRecordCount() {
		return retrievedRecordCount;
	}

	/**
	 * @param retrievedRecordCount the retrievedRecordCount to set
	 */
	public void setRetrievedRecordCount(Integer retrievedRecordCount) {
		this.retrievedRecordCount = retrievedRecordCount;
	}

	/**
	 * @return the accessTime
	 */
	public Long getAccessTime() {
		return accessTime;
	}

	/**
	 * @param accessTime the accessTime to set
	 */
	public void setAccessTime(Long accessTime) {
		this.accessTime = accessTime;
	}

	/** Name of the transaction reading the records **/
	private String transactionName;

	/** Description of the transaction **/
	private String transactionDescription;

	/** Number of records returned in the read operation  **/
	private Integer retrievedRecordCount;

	/** Time the records were accessed **/
	private Long accessTime;
}
