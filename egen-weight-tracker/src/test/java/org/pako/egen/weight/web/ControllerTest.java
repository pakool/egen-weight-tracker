/**
 *
 */
package org.pako.egen.weight.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pako.egen.weight.core.ServiceRunner;
import org.pako.egen.weight.web.bean.IncomingMetricBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This testcase requires that the main application is running. Otherwise, the tests will fail
 *
 * @author Pako Castillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceRunner.class)
public class ControllerTest {

	private TestRestTemplate restTemplate;

	private static final String LOCAL_SERVER = "http://127.0.0.1:8000";

	/**
	 * Test the metrics service
	 */
	@Test
	public void testMetricService() {
		restTemplate = new TestRestTemplate();
		IncomingMetricBean bean = new IncomingMetricBean();
		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(50);

		restTemplate.postForEntity(LOCAL_SERVER + "/metrics/create/", bean, String.class);
	}

	/**
	 * Test the alerts service
	 */
	@Test
	public void testAlertsService() {
		restTemplate = new TestRestTemplate();
		IncomingMetricBean bean = new IncomingMetricBean();
		bean.setTimeStamp(System.currentTimeMillis());
		bean.setValue(50);

		restTemplate.getForEntity(LOCAL_SERVER + "/alerts/read/", List.class);
	}

}
