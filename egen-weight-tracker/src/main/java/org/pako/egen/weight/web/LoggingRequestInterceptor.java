/**
 *
 */
package org.pako.egen.weight.web;

import java.io.IOException;

import org.pako.egen.weight.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

/**
 * @author Pako Castillo
 *
 */
@Service
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

		ClientHttpResponse response = execution.execute(request, body);

		log(request,body,response);

		return response;
	}

	private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
		System.out.println(StringUtils.reflectObject(request));
		System.out.println(new String(body));
		System.out.println(StringUtils.reflectObject(response));
	}
}