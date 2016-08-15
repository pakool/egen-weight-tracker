/**
 *
 */
package org.pako.egen.weight.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.query.Query;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Pako Castillo
 *
 */
@Repository
public class MetricsConnectionDao {

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
		List<MetricEntity> list = new ArrayList<MetricEntity>();

		Query<MetricEntity> query = connectionManager.query(new MetricEntity());

		for(MetricEntity metric : query.fetch()){
			list.add(metric);
		}

		return list;
	}

	/**
	 * Save the specified metric into the DB
	 * @param metric
	 */
	public void saveMetric(MetricEntity metric){
		connectionManager.saveObject(metric);
	}
}
