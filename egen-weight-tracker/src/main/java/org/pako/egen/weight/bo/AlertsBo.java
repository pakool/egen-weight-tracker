/**
 *
 */
package org.pako.egen.weight.bo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pako.egen.weight.db.dao.AlertsDao;
import org.pako.egen.weight.db.entity.AlertEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Main business object used to get data from the DAOs, write data into the DB and validate the information.
 *
 * @author Pako Castillo
 *
 */
@Component
public class AlertsBo {

	@Autowired
	private AlertsDao alertsDao;

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	/**
	 *Load all the alerts from the DB
	 *
	 * @return
	 */
	public List<AlertEntity> getAlertsList(){
		return alertsDao.loadAllAlerts();
	}

	/**
	 * Return the list of alerts ranging in the two timestamps
	 *
	 * @param t1
	 * @param t2
	 * @return
	 */
	public List<AlertEntity> getAlertsList(Long t1, Long t2){
		return alertsDao.loadAlertsForTimeRange(t1, t2);
	}

	/**
	 * Save the specified Alert entity
	 *
	 * @param alert
	 */
	public void addNewAlert(AlertEntity alert){

		try {
			alertsDao.saveAlert(alert);
		} catch (ParameterException e) {
			LOG.error("There was a problem saving the alert ", e);
		}

		LOG.info("Alert " + alert.getId() + " successfully saved");
	}
}
