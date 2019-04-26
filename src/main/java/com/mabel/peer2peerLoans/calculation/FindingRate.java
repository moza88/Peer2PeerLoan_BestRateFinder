package com.mabel.peer2peerLoans.calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.mabel.peer2peerLoans.lendertree.LendingNode;
import com.mabel.peer2peerLoans.lendertree.Remove;

public class FindingRate {

	Remove remove = new Remove();

	LowestRate lowestRate = new LowestRate();
	
	HashMap<Integer, Double> interestRates = new HashMap<Integer, Double>();
	int loanAmountLeft;
	int lenderAvailableFunds;
	int borrowedFunds;
	public double finalRate;
	
	public void findRate(int loanAmount, LendingNode rootLenderNode){
        
		System.out.println("Finding rate for loan amount " + loanAmount);
		//Find the lowest rate
        LendingNode currentLenderNode = lowestRate.findLowestRateNode(rootLenderNode);
                
		lenderAvailableFunds = currentLenderNode.lendingAmount;

        //If the loan amount is less or equal than the lowest interest node 
        //then they will get the lender's proposed interest rate
        if(loanAmount <= lenderAvailableFunds) {
        	
        	loanAmountLeft = loanAmount - currentLenderNode.lendingAmount;

        	lenderAvailableFunds = currentLenderNode.lendingAmount - loanAmount;
        	
        	borrowedFunds = loanAmount;
        	
        	System.out.println(currentLenderNode.lenderName + " lent " + borrowedFunds + " at " + currentLenderNode.rate + " interest rate");

        	//Marking the interest rate they will pay for the borrowed amount
        	interestRates.put(borrowedFunds, currentLenderNode.rate);
        	
        	System.out.println("Loan is Fulfilled");
			System.out.println(interestRates.toString());
			
        	lenderIsRemoved(loanAmount, rootLenderNode, currentLenderNode.rate, currentLenderNode.lenderName);
        	
        //If the loan amount is greater than the lowest interest node available lending amount
        } else if(loanAmount > lenderAvailableFunds) {
        	
        	loanAmountLeft = loanAmount - currentLenderNode.lendingAmount;
        	
        	lenderAvailableFunds =  Math.max(0, currentLenderNode.lendingAmount - loanAmount);

        	borrowedFunds = loanAmount - loanAmountLeft;
        	
        	System.out.println(currentLenderNode.lenderName + " lent " + borrowedFunds + " at " + currentLenderNode.rate + " interest rate");

        	//Marking the interest rate they will pay for the borrowed amount
            interestRates.put(borrowedFunds, currentLenderNode.rate);

        	//lenderIsRemoved(lenderAvailableFunds, rootLenderNode, currentLenderNode.rate, currentLenderNode.lenderName);

    		//Lender no longer has money to lend so we're removing them from the our list of available lenders
            if(lenderAvailableFunds == 0) {
        		Remove.deleteLendingNode(rootLenderNode, currentLenderNode.rate, currentLenderNode.lenderName);
        		System.out.println("Removing " + currentLenderNode.lenderName + " from list of lenders");
        	}
            
        	findRate(loanAmountLeft, rootLenderNode);
        	
        	isLoanFulfilled(loanAmountLeft);
        }
	}
	
	private boolean isLoanFulfilled(int loanAmountLeft) {
		if(loanAmountLeft == 0) {
			System.out.println("Loan is Fulfilled");
			System.out.println(interestRates.toString());
			return true;
		}
		return false;
	}
	
	//private double quotedRate(HashMap<Integer, Double> interestRates, LendingNode rootLenderNode) {
	public double quotedRate(LendingNode rootLenderNode) {
		//TODO: Change this formula this is just a proxy one
		return finalRate=lowestRate.findLowestRateNode(rootLenderNode).rate;
	}
	
	private static void lenderIsRemoved(int lenderAvailableFunds, LendingNode rootLenderNode, double rate, String lenderName) {
		//Lender no longer has money to lend so we're removing them from the our list of available lenders
    	if(lenderAvailableFunds == 0) {
    		Remove.deleteLendingNode(rootLenderNode, rate, lenderName);
    		System.out.println("Removing " + lenderName + " from list of lenders");
    	}
	}
}
