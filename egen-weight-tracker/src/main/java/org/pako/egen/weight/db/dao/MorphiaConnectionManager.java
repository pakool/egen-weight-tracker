/**
 *
 */
package org.pako.egen.weight.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.mapping.cache.DefaultEntityCache;
import org.mongodb.morphia.query.Query;
import org.pako.egen.weight.db.entity.AlertEntity;
import org.pako.egen.weight.db.entity.ExceptionEntity;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.db.entity.ReadAuditEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.pako.egen.weight.util.PropertyManager;
import org.pako.egen.weight.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * Main DB Connection Manager which provides CRUD functionality to any DAO
 * in the system
 *
 * @author Pako Castillo
 *
 */
@Repository
public class MorphiaConnectionManager<T extends Object> {

	@SuppressWarnings("unused")
	private Morphia morphia;

	/** Mongo Datastore to be used **/
	private Datastore ds;

	/** DB Connection **/
	private MongoClient mongoClient;

	/** Injected property manager **/
	@Autowired
	private PropertyManager propertyManager;

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	/**
	 * Load all the entities after the object is constructed
	 *
	 * @throws ParameterException
	 */
	@PostConstruct
	private void loadEntities() throws ParameterException {
		String dbName = propertyManager.getDbName();
		String dbServer = propertyManager.getDbServer();
		String dbPort = propertyManager.getDbPort();

		/** Throw an exception if the database name doesn't exist **/
		if (dbName == null || dbServer == null) {
			throw new ParameterException("The database server or instance name is null");
		}
		Morphia morphia = new Morphia();
		mongoClient = StringUtils.isNumber(dbPort) ? new MongoClient(dbServer, Integer.valueOf(dbPort))
				: new MongoClient(dbServer);
		morphia.map(AlertEntity.class, ExceptionEntity.class, MetricEntity.class, ReadAuditEntity.class);
		ds = morphia.createDatastore(mongoClient, dbName);

	}

	/**
	 * Return the local datastore
	 *
	 * @return
	 */
	public Datastore getDatastore() {
		return ds;
	}

	/**
	 * Execute a query and return the results
	 *
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Query<T> query(T c) {
		return (Query<T>) ds.createQuery(c.getClass());
	}

	/**
	 * Save the specified entity
	 *
	 * @return
	 */
	public Key<T> saveObject(T c) {
		return ds.save(c);
	}

	/**
	 * Remove the specified entity from the DB
	 *
	 * @param entity
	 * @return
	 */
	public WriteResult removeObject(T c) {
		return ds.delete(c);
	}

	/**
	 * Return all the records of type c
	 *
	 * @param c
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllRecords(T c) {
		DBCollection collection = ds.getCollection(c.getClass());
		List<T> list = new ArrayList<T>();
		DBCursor cursor = collection.find();
		Mapper mapper = new Mapper();

		/**
		 * Iterate through all the response, transform each DBObject into T, and
		 * add them to the list
		 **/
		if (cursor.hasNext()) {
			list.add((T) mapper.fromDBObject(ds, c.getClass(), cursor.next(), new DefaultEntityCache()));
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> findRecordsInTimeFrame(Long timeStamp1, Long timeStamp2, T c) {

		if (timeStamp1 != null && timeStamp1 != null) {

			Query<T> timeStampQuery = (Query<T>) ds.createQuery(c.getClass());

			if(timeStamp2 > timeStamp1){
				timeStampQuery.and(
						timeStampQuery.criteria("createDate").greaterThan(timeStamp1),
						timeStampQuery.criteria("createDate").lessThanOrEq(timeStamp2)
						);
			} else {
				timeStampQuery.and(
						timeStampQuery.criteria("createDate").greaterThan(timeStamp2),
						timeStampQuery.criteria("createDate").lessThanOrEq(timeStamp1)
						);
			}

			/** Second option to write exclusively AND
			timeStampQuery
				.field("createDate").greaterThan(timestamp1)
				.field("createDate").lessThanOrEq(timestamp2);
			 **/

			return timeStampQuery.asList();
		}
		return null;
	}
}
