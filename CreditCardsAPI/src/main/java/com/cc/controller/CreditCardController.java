/**
 * 
 */
package com.cc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 */
@RestController
@RequestMapping("creditcards/")
public class CreditCardController {
	
	private static Logger log = LoggerFactory.getLogger(CreditCardController.class);
	
	@Autowired
	private CreditCardRepository ccRepos;
	
	@GetMapping("getAll")
	public List<CreditCard> getAll(){
		log.debug("GET received ");
		return this.ccRepos.findAll();		
	}
	
	@PostMapping(path="add", consumes = {"application/json"})
	public CreditCard  addCreditCard(@RequestBody CreditCard card) {
		log.debug("POST received "+card.toString());
		if(ProjectUtils.validateCardNumber(card.getNumber())) {
			card.setBalance(0.0);
			try {
			ccRepos.save(card);
			}catch (Exception e) {
				throw new RequestFormatException(e.getLocalizedMessage());
			}
			return card;
		}
		else
			throw new InvalidCCNumberException("Invalid credit card number");
	}
}
