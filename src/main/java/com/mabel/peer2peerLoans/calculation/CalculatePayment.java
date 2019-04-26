package com.mabel.peer2peerLoans.calculation;

import org.apache.commons.math3.util.Precision;

import com.mabel.peer2peerLoans.Input;

public class CalculatePayment {
	
	private double monthlyRate;
	
	public double monthlyPayment(double interestRate, int termInMonths, int loanAmount) {
		// Monthly interest rate is the yearly rate divided by 12
	    monthlyRate = interestRate / 12.0;
	    //System.out.println("Monthly Rate: " + monthlyRate);
	     
	    double monthlyPayment = (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
    
	return monthlyPayment;
	}
	
	public double totalRepayment(double monthlyPayment, int termInMonths, int loanAmount) {
		double totalRepayment = 0;
		
		for(int months = 1; months <= termInMonths; months++) {
			totalRepayment = loanAmount * Math.pow((1+ monthlyRate), months);
	    }
	      
			return totalRepayment;

	}
	
}
