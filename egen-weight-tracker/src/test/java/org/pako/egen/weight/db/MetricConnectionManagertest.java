/**
 *
 */
package org.pako.egen.weight.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.pako.egen.weight.db.dao.MorphiaConnectionManager;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.util.ApplicationConfig;
import org.pako.egen.weight.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.WriteResult;
import com.mongodb.client.ListDatabasesIterable;

/**
 * Test the connection to the mongo db
 *
 * @author Pako Castillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@AutoConfigureMockMvc
public class MetricConnectionManagertest {

	@Autowired
	private MorphiaConnectionManager<MetricEntity> connectionManager;

	@Test
	public void testDbConnection(){
		Datastore ds = connectionManager.getDatastore();

		assertNotNull(ds);
		System.out.println("DS Is not null " + StringUtils.reflectObject(ds));
		ListDatabasesIterable<org.bson.Document> l = ds.getMongo().listDatabases();
		assertNotNull(l);

		System.out.println(StringUtils.reflectObject(l));
	}

	@Test
	public void testMetricOpperations(){
		MetricEntity entity = new MetricEntity();

		entity.setBaseWeight(10);
		entity.setCalculatedWeight(15);
		entity.setCreateUserId("SYS");
		entity.setValidMetric("TRUE");

		Key<MetricEntity> key = connectionManager.saveObject(entity);

		assertNotNull(key);
		System.out.println("Successfully inserted key " + key);

		List<MetricEntity> list = connectionManager.findAllRecords(entity);
		assertNotNull(list);
		assertTrue(list.size() > 0);

		System.out.println("Retrieved " + list.size() + " elements ");

		for(MetricEntity fetchedEntity : list) {
			System.out.println(fetchedEntity);
		}

		try {
			System.out.println("Waiting 10 secs. . . ");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WriteResult result = connectionManager.removeObject(entity);
		assertNotNull(result);

		System.out.println("Successfully removed " + StringUtils.reflectObject(result));

	}
}
