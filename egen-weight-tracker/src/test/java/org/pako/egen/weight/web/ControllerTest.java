/**
 *
 */
package org.pako.egen.weight.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.util.ApplicationConfig;
import org.pako.egen.weight.web.bean.IncomingMetricBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Pako Castillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
//@AutoConfigureMockMvc
public class ControllerTest {

	private TestRestTemplate restTemplate;

	private static final String LOCAL_SERVER = "http://127.0.0.1:8000";

	@SuppressWarnings("unchecked")
	//	@Test
	public void testMetricService() {
		restTemplate = new TestRestTemplate();
		IncomingMetricBean bean = new IncomingMetricBean();
		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(50);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		List<MetricEntity> list = new ArrayList<MetricEntity>();

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);


		list = (List<MetricEntity>) restTemplate.getForEntity(
				LOCAL_SERVER + "/metrics/readAll/", List.class);

		System.out.println("Size: " + list.size());
		for(MetricEntity metric : list){
			System.out.println(metric);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(80);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		list = new ArrayList<MetricEntity>();

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);

		list = (List<MetricEntity>) restTemplate.getForEntity(
				LOCAL_SERVER + "/metrics/readAll/", list.getClass());

		System.out.println("Size: " + list.size());
		for(MetricEntity metric : list){
			System.out.println(metric);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(54);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		list = new ArrayList<MetricEntity>();

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);

		list = (List<MetricEntity>) restTemplate.getForEntity(
				LOCAL_SERVER + "/metrics/readAll/", list.getClass());

		System.out.println("Size: " + list.size());
		for(MetricEntity metric : list){
			System.out.println(metric);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(30);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		list = new ArrayList<MetricEntity>();

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);

		list = (List<MetricEntity>) restTemplate.getForEntity(
				LOCAL_SERVER + "/metrics/readAll/", list.getClass());


		System.out.println("Size: " + list.size());
		for(MetricEntity metric : list){
			System.out.println(metric);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}
	}

	@Test
	public void testMetricWriteService() {
		restTemplate = new TestRestTemplate();
		IncomingMetricBean bean = new IncomingMetricBean();
		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(50);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		}

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);

	}


}
