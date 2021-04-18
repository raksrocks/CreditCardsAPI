/**
 * 
 */
package com.cc.api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.cc.model.CreditCard;
import com.google.gson.Gson;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * @author Administrator
 *
 */
public class APITest {
	CreditCard response = new CreditCard();
	
	@Given("API is ready")
	public void prepare_scenario() {
		//Setup
		RestAssured.baseURI = "http://localhost:8080/creditcards/";
	}
	
	@When("^I send a POST request to API with name is ([^\"]*) and number as ([^\"]*) and limit as ([^\"]*)$")
	public void setupPayload(String name, String number, Double limit) throws Throwable {
		postAPI(new CreditCard(name,number,limit,0.0));
	}
	
	@Then("^verify the Status as ([^\"]*)$")
	public void validateResult(String result) throws Throwable {
		 if("success".equalsIgnoreCase(result))
			 assertNotNull(response.getId());
		 else
			 assertNull(response.getNumber());		 
	}
	
	/**
	 * @param body
	 * @return
	 */
	private CreditCard postAPI(CreditCard body) {
		try {		
			response =  new Gson().fromJson(given()
		         .contentType(ContentType.JSON)
		         .body(body)
		         .post("add")
		         .then()
		         .extract()
		         .response().asPrettyString(), CreditCard.class);
		 }catch (Exception e) {
			 e.printStackTrace();
			fail("Unable to load the URL");
			response = null;
		}		 
		return response;
	 }
}
