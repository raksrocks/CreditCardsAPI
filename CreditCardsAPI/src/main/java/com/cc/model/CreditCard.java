/**
 * 
 */
package com.cc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 *	Bean class for credit card
 */
@Entity
@Table(name = "creditcard")
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;	
	@Column(name="name")
	private String name;
	@Column(name="number")
	private String number;	
	@Column(name="limit_amount")
	private Double limitAmount;
	@Column(name="balance")
	private Double balance;		

	public CreditCard() {
		
	}
	/**
	 * @param name
	 * @param number
	 * @param limit
	 * @param balance
	 */
	public CreditCard(String name, String number, Double limit, Double balance) {
		super();
		this.name = name;
		this.number = number;
		this.limitAmount = limit;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", name=" + name + ", number=" + number + ", limit=" + limitAmount + ", balance="
				+ balance + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Double getLimit() {
		return limitAmount;
	}
	public void setLimit(Double limit) {
		this.limitAmount = limit;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
