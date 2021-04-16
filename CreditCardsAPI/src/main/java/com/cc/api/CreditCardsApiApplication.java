package com.cc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cc.model.CreditCard;
import com.cc.repo.CreditCardRepository;

@SpringBootApplication (scanBasePackages={
		"com.cc.repo","com.cc.model"})
@ComponentScan({"com.cc.controller"})
@EntityScan("com.cc.model")
@EnableJpaRepositories("com.cc.repo")
public class CreditCardsApiApplication  implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(CreditCardsApiApplication.class, args);
		System.out.println("after updating starters");
	}

	@Autowired
	private CreditCardRepository ccRepo;

	
	  @Override 
	  public void run(String... args) throws Exception {
	  this.ccRepo.save(new CreditCard("Rakes1h","123456789012",0.220,602.70)); 
	  this.ccRepo.save(new CreditCard("Rakesh2","123456789012",30.20,02.0));
	  this.ccRepo.save(new CreditCard("Rakesh3","123456789012",05.20,502.08));
	  this.ccRepo.save(new CreditCard("Rakesh4","123456789012",50.20,082.0));
	 
	  }
	 

}
