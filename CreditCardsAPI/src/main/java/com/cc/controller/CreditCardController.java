/**
 * 
 */
package com.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Autowired
	private CreditCardRepository ccRepos;
	
	@GetMapping("getAll")
	public List<CreditCard> getAll(){
		return this.ccRepos.findAll();		
	}
	
	@PostMapping(path="add", consumes = {"application/json"})
	public CreditCard addCreditCard(@RequestBody CreditCard card) {
		if(ProjectUtils.validateCardNumber(card.getNumber())) {
			card.setBalance(0.0);
			ccRepos.save(card);
		}
		
		//Send error response
		return card;
	}
}
