/**
 * 
 */
package com.cc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.exceptions.InvalidCCNumberException;
import com.cc.exceptions.RequestFormatException;
import com.cc.model.CreditCard;
import com.cc.repo.CreditCardRepository;
import com.cc.utils.ProjectUtils;

/**
 * @author Administrator
 * 
 * This Controller exposes 2 end points
 * <b>End point</b> : /creditcards/
 * <b>GET</b>: /getAll : returns all the credit cards available 
 * <b>POST</b>: /add : creates a credit card when passed valid input
 * 
 * Uses In Memory H2 DB to store the data
 *
 */
@RestController
@RequestMapping("creditcards/")
public class CreditCardController {
	
	private static Logger log = LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	private CreditCardRepository ccRepos;
	
	/**
	 * @return 
	 * 
	 * returns a List object with all the credit cards in the DB
	 * ToDo: implement pagination to return chunks of cards than whole set
	 */
	@GetMapping("getAll")
	public ResponseEntity<List<CreditCard>> getAll(){
		log.debug("GET received ");
		try {
			return new ResponseEntity<List<CreditCard>>(this.ccRepos.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error occurred in /getAll "+ e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * @param card
	 * @return
	 * 
	 * Accepts Creditcard in request body.
	 * Name
	 * Number
	 * Limit
	 * Balance
	 * 
	 * throws InvalidCCNumberException
	 * throws RequestFormatException
	 * throws Exception
	 */
	@PostMapping(path="add", consumes = {"application/json"})
	public ResponseEntity<CreditCard>  addCreditCard(@RequestBody CreditCard card) {
		log.debug("POST received "+card.toString());
		
		try {
			//Validate the input 
			ProjectUtils.validateInput(card);
			
			//persist the data to DB
			ccRepos.save(card);
			
			log.debug("entity created "+card.toString());
		}catch (InvalidCCNumberException e) {
			log.error("InvalidCCNumberException error occurred while processing POST request in /add "+e.getLocalizedMessage());
			throw e;
		}catch (RequestFormatException e) {
			log.error("RequestFormatException error occurred while processing POST request in /add "+e.getLocalizedMessage());
			throw e;
		}catch (Exception e) {
			log.error("Unexpected error occurred while processing POST request in /add "+e.getLocalizedMessage());
			throw e;
		}
		
		return new ResponseEntity<CreditCard>(card,HttpStatus.CREATED);		
	}
}
