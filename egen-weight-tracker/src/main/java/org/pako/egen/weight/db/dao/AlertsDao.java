/**
 *
 */
package org.pako.egen.weight.db.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pako.egen.weight.db.entity.AlertEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Data Access for the Alerts Entity
 *
 * @author Pako Castillo
 *
 */
@Repository
public class AlertsDao {

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	@Autowired
	private MorphiaConnectionManager<AlertEntity> connectionManager;

	/**
	 * Load all the alerts from the db and return them as list
	 *
	 * @return
	 */
	public List<AlertEntity> loadAllAlerts(){

		return connectionManager.query(new AlertEntity()).asList();
	}

	/**
	 * Save the specified alert into the DB
	 *
	 * @param alert
	 * @throws ParameterException
	 */
	public void saveAlert(AlertEntity alert) throws ParameterException{

		/** Sanity check **/
		if(alert == null){
			throw new ParameterException("The provided alert is null");
		}

		connectionManager.saveObject(alert);
	}

	/**
	 * Return a list of Alert Entries based on the specified time range. The first parameter should be smaller than the second one
	 *
	 * @param timeStamp1
	 * @param timeStamp2
	 * @return
	 */
	public List<AlertEntity> loadAlertsForTimeRange(Long timeStamp1, Long timeStamp2){
		/** If both TimeStamps are present, fetch the records matching the time range **/
		if(timeStamp1 != null && timeStamp2 !=null){
			return connectionManager.findRecordsInTimeFrame(timeStamp1, timeStamp2, new AlertEntity());
			/** If one of the parameters is null but the other is present, use current time as filter **/
		} else if(timeStamp1 != null){
			return connectionManager.findRecordsInTimeFrame(timeStamp1, System.currentTimeMillis(), new AlertEntity());
		} else if(timeStamp2 != null){
			return connectionManager.findRecordsInTimeFrame(System.currentTimeMillis(), timeStamp2, new AlertEntity());
		}

		return null;
	}
}
