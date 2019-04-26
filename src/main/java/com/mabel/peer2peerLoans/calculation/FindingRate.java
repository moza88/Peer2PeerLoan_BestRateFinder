package com.mabel.peer2peerLoans.calculation;

import java.util.HashMap;
import com.mabel.peer2peerLoans.Input;
import com.mabel.peer2peerLoans.lendertree.LendingNode;
import com.mabel.peer2peerLoans.lendertree.Remove;

public class FindingRate {

	Input input = new Input();
	Remove remove = new Remove();

	LowestRate lowestRate = new LowestRate();
	
	HashMap<Integer, Double> interestRates = new HashMap<Integer, Double>();
	int loanAmountLeft;
	int lenderAvailableFunds;
	int borrowedFunds;
	public double finalRate;
	
	public void findRate(int loanAmount, LendingNode rootLenderNode){
        
		//System.out.println("Finding rate for loan amount " + loanAmount);
		//Find the lowest rate
        LendingNode currentLenderNode = LowestRate.findLowestRateNode(rootLenderNode);
                
		lenderAvailableFunds = currentLenderNode.lendingAmount;

        //If the loan amount is less or equal than the lowest interest node 
        //then they will get the lender's proposed interest rate
        if(loanAmount <= lenderAvailableFunds) {
        	
        	loanAmountLeft = loanAmount - currentLenderNode.lendingAmount;

        	lenderAvailableFunds = currentLenderNode.lendingAmount - loanAmount;
        	
        	borrowedFunds = loanAmount;
        	
        	//System.out.println(currentLenderNode.lenderName + " lent " + borrowedFunds + " at " + currentLenderNode.rate + " interest rate");

        	//Marking the interest rate they will pay for the borrowed amount
        	interestRates.put(borrowedFunds, currentLenderNode.rate);
        	
        	//System.out.println("Loan is Fulfilled");
			//System.out.println(interestRates.toString());
			
        	lenderIsRemoved(loanAmount, rootLenderNode, currentLenderNode.rate, currentLenderNode.lenderName);
        	
        //If the loan amount is greater than the lowest interest node available lending amount
        } else if(loanAmount > lenderAvailableFunds) {
        	
        	loanAmountLeft = loanAmount - currentLenderNode.lendingAmount;
        	
        	lenderAvailableFunds =  Math.max(0, currentLenderNode.lendingAmount - loanAmount);

        	borrowedFunds = loanAmount - loanAmountLeft;
        	
        	//System.out.println(currentLenderNode.lenderName + " lent " + borrowedFunds + " at " + currentLenderNode.rate + " interest rate");

        	//Marking the interest rate they will pay for the borrowed amount
            interestRates.put(borrowedFunds, currentLenderNode.rate);

    		//Lender no longer has money to lend so we're removing them from the our list of available lenders
            if(lenderAvailableFunds == 0) {
        		Remove.deleteLendingNode(rootLenderNode, currentLenderNode.rate, currentLenderNode.lenderName);
        		//System.out.println("Removing " + currentLenderNode.lenderName + " from list of lenders");
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
	
	public double quotedRate(LendingNode rootLenderNode) {
		double finalRate = 0;
		double weight;
		double weightedRate;
	
		/*Calculating the average weighted average
		 * The weights are amount taken / total loan amount
		 * We take those weights and we multiply it by the interest rates
		 * This way if a customers takes out a small loan from a high interest rate 
		 * peer loan the rate will accurately reflect that
		 */
		for (int key : interestRates.keySet()) {
		    weight = (double)key/(double)input.getLoanAmount();
		    weightedRate = (double) (weight*interestRates.get(key));
		    finalRate = finalRate + weightedRate;
		}

		return finalRate;
	}
	
	private static void lenderIsRemoved(int lenderAvailableFunds, LendingNode rootLenderNode, double rate, String lenderName) {
		//Lender no longer has money to lend so we're removing them from the our list of available lenders
    	if(lenderAvailableFunds == 0) {
    		Remove.deleteLendingNode(rootLenderNode, rate, lenderName);
    		//System.out.println("Removing " + lenderName + " from list of lenders");
    	}
	}
}
