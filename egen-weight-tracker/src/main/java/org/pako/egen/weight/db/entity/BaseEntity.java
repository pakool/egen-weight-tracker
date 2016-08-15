/**
 *
 */
package org.pako.egen.weight.db.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;
import org.pako.egen.weight.util.StringUtils;

/**
 * Base POJO containing shared data across all Mongo objects
 *
 * @author Pako Castillo
 *
 */
public abstract class BaseEntity {

	@Id
	@Property("id")
	protected ObjectId id;

	@Version
	@Property("version")
	private Long version;

	/** Java timestamp representing the time when the object was created. Defaults to sysdate **/
	private Long createDate = System.currentTimeMillis();

	/** Java timestamp representing the time when the object was updated **/
	private Long updateDate = System.currentTimeMillis();

	/** Username responsible for creating this object **/
	private String createUserId;

	/** Username responsible for updating this object **/
	private String updateUserId;

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the createDate
	 */
	public Long getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Long getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId the updateUserId to set
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return StringUtils.reflectObject(this);
	}
}
