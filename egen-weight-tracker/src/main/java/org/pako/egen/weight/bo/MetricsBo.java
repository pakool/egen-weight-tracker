/**
 *
 */
package org.pako.egen.weight.bo;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.api.RulesEngine;
import org.pako.egen.weight.db.dao.MetricsDao;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.pako.egen.weight.rules.WeightRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Main business object used to get data from the DAOs, write data into the DB and validate the information.
 *
 * @author Pako Castillo
 *
 */
@Component
public class MetricsBo {

	@Autowired
	private MetricsDao metricsDao;

	@Autowired
	private WeightRule weightRule;

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	/**
	 * Do the validation of the weight
	 *
	 * @param baseWeight
	 * @param currentWeight
	 */
	public boolean isValidWeight(MetricEntity metric){
		RulesEngine rulesEngine = aNewRulesEngine().build();

		weightRule.setMetric(metric);

		rulesEngine.fireRules();

		/** If one of the rules matched the weight, add the corresponding states to the object **/
		if(weightRule.hasUnderWeight()){
			metric.setValidMetric("FALSE");
			metric.setInvalidDescription("UNDERWEIGHTED RECORD");
		} else if(weightRule.hasOverWeight()){
			metric.setValidMetric("FALSE");
			metric.setInvalidDescription("OVERWEIGHTED RECORD");
		} else {
			metric.setValidMetric("TRUE");
		}

		/** If true, the alert was created and is not a valid metric **/
		return !weightRule.hasAlertCreated();
	}

	/**
	 *Load all the metrics from the DB
	 *
	 * @return
	 */
	public List<MetricEntity> getMetricsList(){
		return metricsDao.loadAllMetrics();
	}

	/**
	 * Return the list of metrics ranging in the two timestamps
	 *
	 * @param t1
	 * @param t2
	 * @return
	 */
	public List<MetricEntity> getMetricsList(Long t1, Long t2){
		return metricsDao.loadMetricsForTimeRange(t1, t2);
	}

	/**
	 * Trigger the validation rules and store the record in the database
	 *
	 * @note According to the specs, there is no filtering required after applying the rules. Meaning that
	 *       the metric records should still be added to the db despite their under/over weight state; however
	 *       such states will be kept in the records to keep track on what the issue is
	 *
	 * @param metric
	 */
	public void addNewMetric(MetricEntity metric){

		/** Trigger the validation rule only **/
		if(!isValidWeight(metric)){
			LOG.warn("Metric with ID " + metric.getId() + " has an invalid weight " );
		}

		try {
			metricsDao.saveMetric(metric);
		} catch (ParameterException e) {
			LOG.error("There was a problem saving the metric ", e);
		}
		LOG.info("Metric " + metric.getId() + " successfully saved");
	}
}
