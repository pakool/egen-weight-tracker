/**
 *
 */
package org.pako.egen.weight.web;

import java.util.List;

import org.pako.egen.weight.bo.MetricsBo;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fcastilloguerrero
 *
 */
@Controller
@RequestMapping("/metrics")
public class MetricsApiService {

	/** Inject the metrics business object **/
	@Autowired
	private MetricsBo metricsBo;

	/**
	 * Expose the service returning the list of all the metrics
	 *
	 * @return
	 */
	@RequestMapping(value = "/read/", method = RequestMethod.GET)
	public ResponseEntity<List<MetricEntity>> listAllUsers() {
		List<MetricEntity> metrics = metricsBo.getMetricsList();
		if (metrics.isEmpty()) {
			return new ResponseEntity<List<MetricEntity>>(HttpStatus.NO_CONTENT);// You

		}
		return new ResponseEntity<List<MetricEntity>>(metrics, HttpStatus.OK);
	}
}
