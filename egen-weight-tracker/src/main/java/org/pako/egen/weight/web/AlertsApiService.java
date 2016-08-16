/**
 *
 */
package org.pako.egen.weight.web;

import java.util.List;

import org.pako.egen.weight.bo.AlertsBo;
import org.pako.egen.weight.db.entity.AlertEntity;
import org.pako.egen.weight.web.bean.IncomingRangeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pako Castillo
 *
 */
@Controller
@RequestMapping("/alerts")
public class AlertsApiService {

	@Autowired
	private AlertsBo alertsBo;

	/**
	 * Expose the service returning the list of all the alerts
	 *
	 * @return
	 */
	@RequestMapping(value = "/read/", method = RequestMethod.GET)
	public ResponseEntity<List<AlertEntity>> listAllUsers() {
		List<AlertEntity> alerts = alertsBo.getAlertsList();
		if (alerts.isEmpty()) {
			return new ResponseEntity<List<AlertEntity>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<AlertEntity>>(alerts, HttpStatus.OK);
	}

	/**
	 * Expose the service returning the list of all the alerts
	 *
	 * @return
	 */
	@RequestMapping(value = "/readByRange/", method = RequestMethod.POST)
	public ResponseEntity<List<AlertEntity>> listAllalertsInRange(@RequestBody IncomingRangeBean incomingRangeBean) {
		List<AlertEntity> alerts = alertsBo.getAlertsList(incomingRangeBean.getInitialTimeStamp(), incomingRangeBean.getEndingTimeStamp());
		if (alerts.isEmpty()) {
			return new ResponseEntity<List<AlertEntity>>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<List<AlertEntity>>(alerts, HttpStatus.OK);
	}


}
