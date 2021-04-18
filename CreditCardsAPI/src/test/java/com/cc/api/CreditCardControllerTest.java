/**
 * 
 */
package com.cc.api;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cc.model.CreditCard;
import com.cc.repo.CreditCardRepository;


/**
 * @author Administrator
 *
 */

@SpringBootTest
public class CreditCardControllerTest {
	
	@MockBean
	private CreditCardRepository repo;
	
	@Test
	public void testGetAll() {
		// setup mock repo
		CreditCard card = new CreditCard("Test_Success", "4716550166817131", 99.99, 10.0);
		doReturn(card).when(repo).findByName("Test_Success");
		
		CreditCard returnedcard = repo.findByName("Test_Success");
		
		Assertions.assertEquals("Test_Success",returnedcard.getName());
	}
}
