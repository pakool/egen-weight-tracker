/**
 *
 */
package org.pako.egen.weight.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Contains all the loaded properties from the system
 *
 * @author Pako Castillo
 *
 */
@Component
public class PropertyManager {

	/** Dynamic configuration for the maximum acceptable weight before creating an alert **/
	@Value("${application.config.max.weight}")
	private Integer maxAcceptableWeight;

	/** Dynamic configuration for the minimum acceptable weight before creating an alert **/
	@Value("${application.config.min.weight}")
	private Integer minAcceptableWeight;

	/** Name of the mongo db instance **/
	@Value("${application.config.db.name}")
	private String dbName;

	/** Name of the mongo db instance **/
	@Value("${application.config.db.server}")
	private String dbServer;

	/** Name of the mongo db instance **/
	@Value("${application.config.db.port}")
	private String dbPort;

	/**
	 * Return the Maximum Acceptable Weight
	 *
	 * @return
	 */
	public Integer getMaxAcceptableWeight(){
		return maxAcceptableWeight;
	}

	/**
	 * Return the Minimum Acceptable Weight
	 *
	 * @return
	 */
	public Integer getMinAcceptableWeight(){
		return minAcceptableWeight;
	}

	/**
	 * Return the MongoDB name to be used
	 *
	 * @return
	 */
	public String getDbName(){
		return dbName;
	}

	/**
	 * Return the server address for MongoDB
	 *
	 * @return
	 */
	public String getDbServer(){
		return dbServer;
	}

	/**
	 * Return the db server port
	 *
	 * @return
	 */
	public String getDbPort(){
		return dbPort;
	}
}
