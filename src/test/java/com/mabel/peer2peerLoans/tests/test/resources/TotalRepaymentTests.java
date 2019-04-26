package com.mabel.peer2peerLoans.tests.test.resources;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mabel.peer2peerLoans.calculation.CalculatePayment;

public class TotalRepaymentTests {

	@Test
	public void totalRepayment_1000_36mo_7rate() {
		CalculatePayment calculatePayment = new CalculatePayment();
		
		int loanAmount = 1000;
		int termInMonths = 36;
		double interestRate = 0.07;
		double monthlyPayment = calculatePayment.monthlyPayment(interestRate, termInMonths, loanAmount);
		
		double totalRepayment = calculatePayment.totalRepayment(monthlyPayment, termInMonths, loanAmount);
		
		assertSame(totalRepayment, 1000);
	}
	
	@Test
	public void totalRepayment_2000_36mo_7rate() {
		CalculatePayment calculatePayment = new CalculatePayment();
		
		int loanAmount = 2000;
		int termInMonths = 36;
		double interestRate = 0.07;
		double monthlyPayment = calculatePayment.monthlyPayment(interestRate, termInMonths, loanAmount);
		
		double totalRepayment = calculatePayment.totalRepayment(monthlyPayment, termInMonths, loanAmount);
		
		assertSame(totalRepayment, 1000);
	}
	
	@Test
	public void totalRepayment_2000_36mo_10rate() {
		CalculatePayment calculatePayment = new CalculatePayment();
		
		int loanAmount = 2000;
		int termInMonths = 36;
		double interestRate = 0.10;
		double monthlyPayment = calculatePayment.monthlyPayment(interestRate, termInMonths, loanAmount);
		
		double totalRepayment = calculatePayment.totalRepayment(monthlyPayment, termInMonths, loanAmount);
		
		assertSame(totalRepayment, 1000);
	}

	@Test
	public void totalRepayment_1000_12mo_10rate() {
		CalculatePayment calculatePayment = new CalculatePayment();
		
		int loanAmount = 1000;
		int termInMonths = 12;
		double interestRate = 0.10;
		double monthlyPayment = calculatePayment.monthlyPayment(interestRate, termInMonths, loanAmount);
		
		double totalRepayment = calculatePayment.totalRepayment(monthlyPayment, termInMonths, loanAmount);
		
		assertSame(totalRepayment, 1000);
	}
	
}
