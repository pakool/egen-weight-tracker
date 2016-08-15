/**
 *
 */
package org.pako.egen.weight.db.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Pako Castillo
 *
 */
@Repository
public class MetricsDao {

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	@Autowired
	private MorphiaConnectionManager<MetricEntity> connectionManager;

	/**
	 * Load all the metrics from the db and return them as list
	 *
	 * @return
	 */
	public List<MetricEntity> loadAllMetrics(){

		return connectionManager.query(new MetricEntity()).asList();
	}

	/**
	 * Save the specified metric into the DB
	 * @param metric
	 * @throws ParameterException
	 */
	public void saveMetric(MetricEntity metric) throws ParameterException{

		/** Sanity check **/
		if(metric == null){
			throw new ParameterException("The provided metric is null");
		}

		connectionManager.saveObject(metric);
	}

	/**
	 * Return a list of MetricEntities based on the specified time range. The first parameter should be smaller than the second one
	 *
	 * @param timeStamp1
	 * @param timeStamp2
	 * @return
	 */
	public List<MetricEntity> loadMetricsForTimeRange(Long timeStamp1, Long timeStamp2){
		/** If both TimeStamps are present, fetch the records matching the time range **/
		if(timeStamp1 != null && timeStamp2 !=null){
			return connectionManager.findRecordsInTimeFrame(timeStamp1, timeStamp2, new MetricEntity());
			/** If one of the parameters is null but the other is present, use current time as filter **/
		} else if(timeStamp1 != null){
			return connectionManager.findRecordsInTimeFrame(timeStamp1, System.currentTimeMillis(), new MetricEntity());
		} else if(timeStamp2 != null){
			return connectionManager.findRecordsInTimeFrame(System.currentTimeMillis(), timeStamp2, new MetricEntity());
		}

		return null;
	}
}
