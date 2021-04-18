package com.cc.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cc.model.CreditCard;

@SpringBootTest
class CreditCardsApiApplicationTests {
	RestTemplate template = new RestTemplate();
	final String baseUrl = "http://localhost:8080/creditcards";
	
		
	@Test
	void testGetCreditCardsSuccess() throws URISyntaxException {
		URI uri = new URI(baseUrl+"/getAll");
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> result= template.getForEntity(uri, List.class);
		Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}
	
	@Test
	void testPostCardDataSuccess() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Success", "4716550166817131", 99.99, 0.0);
		URI uri = new URI(baseUrl+"/add");		
		HttpEntity<CreditCard> request  = new HttpEntity<>(card);
		
		ResponseEntity<CreditCard> result = template.postForEntity(uri, request, CreditCard.class);
		
		Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
		Assertions.assertEquals(0.0, result.getBody().getBalance());
		Assertions.assertEquals(count+1, getCreditCards().size());
		Assertions.assertNotNull(result.getBody().getId());
	}
	
	@Test
	void testPostFailsWithInvalidCardNumber() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Failure", "47165509999817131", 99.99, 0.0);
		URI uri = new URI(baseUrl+"/add");		
		try {
			HttpEntity<CreditCard> request  = new HttpEntity<>(card);
			//ResponseEntity<CreditCard> result = null;
		
			ResponseEntity<CreditCard> result = template.postForEntity(uri, request, CreditCard.class);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
			Assertions.assertEquals(count, getCreditCards().size());
			Assertions.assertNotNull(result.getBody().getId());
		}catch (Exception e) {
			//Assertions.assertTrue(e instanceof HttpClientErrorException);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), ((HttpClientErrorException) e).getRawStatusCode());
			Assertions.assertEquals("{\"status\":\"BAD_REQUEST\",\"message\":\"invalid credit card number\",\"errors\":[\"Input Credit Card number '47165509999817131' is not valid as per Luhn10 rules\"]}", ((HttpClientErrorException) e).getResponseBodyAsString());
			Assertions.assertEquals(count, getCreditCards().size());
		}		
	}
	
	@Test
	void testPostFailsWithInvalidCardNumberDigits() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Failure", "471655099998171312345679", 99.99, 0.0);
		URI uri = new URI(baseUrl+"/add");		
		try {
			HttpEntity<CreditCard> request  = new HttpEntity<>(card);
			//ResponseEntity<CreditCard> result = null;
		
			ResponseEntity<CreditCard> result = template.postForEntity(uri, request, CreditCard.class);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
			Assertions.assertEquals(count, getCreditCards().size());
			Assertions.assertNotNull(result.getBody().getId());
		}catch (Exception e) {
			//Assertions.assertTrue(e instanceof HttpClientErrorException);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), ((HttpClientErrorException) e).getRawStatusCode());
			Assertions.assertEquals("{\"status\":\"BAD_REQUEST\",\"message\":\"invalid input\",\"errors\":[\"Input value for 'number' is not valid\"]}", ((HttpClientErrorException) e).getResponseBodyAsString());
			Assertions.assertEquals(count, getCreditCards().size());
		}		
	}
	@Test
	void testAddWithNonZeroBalance() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Success", "4716550166817131", 99.99, 10.0); // sending balance 10.0 in the request
		URI uri = new URI(baseUrl+"/add");		
		HttpEntity<CreditCard> request  = new HttpEntity<>(card);
		
		try {
			template.postForEntity(uri, request, CreditCard.class);
		}
		catch (HttpClientErrorException e) {
			Assertions.assertTrue(e instanceof HttpClientErrorException);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), e.getRawStatusCode());
			Assertions.assertEquals("{\"status\":\"BAD_REQUEST\",\"message\":\"invalid input\",\"errors\":[\"Input value for 'Balance' is not valid\"]}", e.getResponseBodyAsString());
			Assertions.assertEquals(count, getCreditCards().size());
		}
	}
	
	@Test
	void testAddWithNoInputBalance() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Success", "4716550166817131", 99.99, null); // sending balance 10.0 in the request
		URI uri = new URI(baseUrl+"/add");		
		HttpEntity<CreditCard> request  = new HttpEntity<>(card);
		try {
			template.postForEntity(uri, request, CreditCard.class);
		}
		catch (HttpClientErrorException e) {
			Assertions.assertTrue(e instanceof HttpClientErrorException);
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), e.getRawStatusCode());
			Assertions.assertEquals("{\"status\":\"BAD_REQUEST\",\"message\":\"invalid input\",\"errors\":[\"Input value for 'Balance' is not valid\"]}", e.getResponseBodyAsString());
			Assertions.assertEquals(count, getCreditCards().size());
		}
	}
	
	@Test
	void testAddWithInvalidInput() throws URISyntaxException {
		int count =  getCreditCards().size();
		CreditCard card = new CreditCard("Test Success", null, 99.99, null); // sending balance 10.0 in the request
		URI uri = new URI(baseUrl+"/add");		
		HttpEntity<CreditCard> request  = new HttpEntity<>(card);
		
		try {
			template.postForEntity(uri, request, CreditCard.class);
		}
		catch (HttpClientErrorException e) {
			Assertions.assertTrue(e instanceof HttpClientErrorException);
			
			Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), e.getRawStatusCode());
			Assertions.assertEquals("{\"status\":\"BAD_REQUEST\",\"message\":\"invalid input\",\"errors\":[\"Input value for 'number' is not valid\"]}", e.getResponseBodyAsString());
			Assertions.assertEquals(count, getCreditCards().size());
		}		
	}
	@SuppressWarnings("unchecked")
	private List<CreditCard> getCreditCards() throws URISyntaxException{
		URI uri = new URI(baseUrl+"/getAll");		
		return (List<CreditCard>)template.getForEntity(uri, List.class).getBody();
	}

}
