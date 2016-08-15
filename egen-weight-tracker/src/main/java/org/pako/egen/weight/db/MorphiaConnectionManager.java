/**
 *
 */
package org.pako.egen.weight.db;

import javax.annotation.PostConstruct;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.pako.egen.weight.db.entity.AlertEntity;
import org.pako.egen.weight.db.entity.BaseEntity;
import org.pako.egen.weight.db.entity.ExceptionEntity;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.db.entity.ReadAuditEntity;
import org.pako.egen.weight.db.entity.ReportingEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.pako.egen.weight.util.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

/**
 * @author Pako Castillo
 *
 */
@Repository
public class MorphiaConnectionManager {

	@Autowired
	private Morphia morphia;

	/** Mongo Datastore to be used **/
	private Datastore ds;

	/** DB Connection **/
	private MongoClient mongoClient;

	/** Injected property manager **/
	@Autowired
	private PropertyManager propertyManager;

	/**
	 * Load all the entities after the object is constructed
	 * @throws ParameterException
	 */
	@PostConstruct
	private void loadEntities() throws ParameterException{
		String dbName = propertyManager.getDbName();

		/** Throw an exception if the database name doesn't exist **/
		if(dbName == null){
			throw new ParameterException("The database name is null");
		}

		mongoClient = new MongoClient("localhost");
		morphia.map(AlertEntity.class, BaseEntity.class, ExceptionEntity.class, MetricEntity.class, ReadAuditEntity.class, ReportingEntity.class);
		ds = morphia.createDatastore(mongoClient, dbName);

	}

	/**
	 * Return the local datastore
	 * @return
	 */
	public Datastore getDatastore(){
		return ds;
	}

	/**
	 * Execute a query and return the results
	 *
	 * @param c
	 * @return
	 */
	public Query<?> query(Class<?> c){
		return ds.createQuery(c);
	}

	/**
	 * Save the specified entity
	 */
	public void saveObject(BaseEntity entity){
		ds.save(entity);
	}
}
