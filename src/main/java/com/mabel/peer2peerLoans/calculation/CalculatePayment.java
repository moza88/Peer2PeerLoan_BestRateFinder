package com.mabel.peer2peerLoans.calculation;

public class CalculatePayment {
	
	private double totalRepayment;
	private double monthlyRate;
	
	public double monthlyPayment(double interestRate, int termInMonths, int loanAmount) {
	
	    // Monthly interest rate 
	    // is the yearly rate divided by 12
	     
	    monthlyRate = interestRate / 12.0;
	    System.out.println("Monthly Rate: " + monthlyRate);
	    // Calculate the monthly payment
	    // Typically this formula is provided so 
	    // we won't go into the details
	     
	    // The Math.pow() method is used calculate values raised to a power
	     
	    double monthlyPayment = 
	       (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
    
	return monthlyPayment;
	}
	
	public double totalRepayment(double monthlyPayment, int termInMonths, int loanAmount) {
		//return totalRepayment = (monthlyPayment*termInMonths) + loanAmount;
		return loanAmount * Math.pow((1 + monthlyRate/100),termInMonths);
	}
	
}
