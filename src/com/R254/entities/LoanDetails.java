package com.R254.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "loanDetails")
public class LoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;

	@Column
	private double payOrBorrow;

	@Column
	private double balance;

	@Temporal(TemporalType.DATE)
	private Date date;

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getPayOrBorrow() {
		return payOrBorrow;
	}

	public void setPayOrBorrow(double payOrBorrow) {
		this.payOrBorrow = payOrBorrow;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
