/**
 *
 */
package org.pako.egen.weight.web;

import java.util.List;

import org.pako.egen.weight.bo.MetricsBo;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.web.bean.IncomingMetricBean;
import org.pako.egen.weight.web.bean.IncomingRangeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fcastilloguerrero
 *
 */
@RestController
@RequestMapping("/metrics")
public class MetricsApiService {

	/** Inject the metrics business object **/
	@Autowired
	private MetricsBo metricsBo;

	/** Initial Weight. This is represented by the first call of the service **/
	private static int initialWeight;

	/** Current count of the service calls **/
	private static int currentCount;

	private static final String SERVICE_USER_ID = "WMAS";

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

	/**
	 * Expose the service returning the list of all the metrics
	 *
	 * @return
	 */
	@RequestMapping(value = "/readByRange/", method = RequestMethod.GET)
	public ResponseEntity<List<MetricEntity>> listAllMetricsInRange(@RequestBody IncomingRangeBean incomingRangeBean) {
		List<MetricEntity> metrics = metricsBo.getMetricsList(incomingRangeBean.getInitialTimeStamp(), incomingRangeBean.getEndingTimeStamp());
		if (metrics.isEmpty()) {
			return new ResponseEntity<List<MetricEntity>>(HttpStatus.NO_CONTENT);// You

		}
		return new ResponseEntity<List<MetricEntity>>(metrics, HttpStatus.OK);
	}


	/**
	 * Create a new metric with the specified parameters
	 *
	 * @param incomingMetricBean
	 * @return
	 */
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public ResponseEntity<Void> createMetric(@RequestBody IncomingMetricBean incomingMetricBean) {
		System.out.println("New create request!!!. . . . " + incomingMetricBean);
		if(incomingMetricBean != null){
			if(currentCount++ == 0){
				initialWeight = incomingMetricBean.getValue();
			}

			MetricEntity entity = new MetricEntity();
			entity.setBaseWeight(initialWeight);
			entity.setCalculatedWeight(incomingMetricBean.getValue());
			entity.setMeasurementTime(incomingMetricBean.getTimeStamp());
			entity.setCreateUserId(SERVICE_USER_ID);

			metricsBo.addNewMetric(entity);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
