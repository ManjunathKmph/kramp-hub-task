package com.manju.kramphub.task;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.manju.kramphub.task.exceptions.model.ErrorDto;
import com.manju.kramphub.task.model.KrampHubTaskResponse;

/**
 * Test class for kramp hub task search service api.
 * 
 * @author manju
 * @version 1.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KrampHubTaskRestTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8888/task";
	
	public static final String CREDENTIALS = "manjunath:manjunath123";
	
	@Test
	public void testSearch() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<KrampHubTaskResponse> response = restTemplate.exchange(REST_SERVICE_URI + "/search?q=java", HttpMethod.GET, request, KrampHubTaskResponse.class);
		assertTrue(response.getBody().getTaskResponses().size() > 0);
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void testEmptySearch() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(CREDENTIALS.getBytes())));
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<ErrorDto> response = restTemplate.exchange(REST_SERVICE_URI + "/search?q= ", HttpMethod.GET, request, ErrorDto.class);
		assertTrue(response.getBody().getErrorCode().equals("400"));
	}
}
