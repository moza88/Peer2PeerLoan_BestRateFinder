package com.mabel.peer2peerLoans;

import java.io.IOException;

import com.mabel.peer2peerLoans.calculation.CalculatePayment;
import com.mabel.peer2peerLoans.calculation.FindingRate;
import com.mabel.peer2peerLoans.lendertree.LendingInfoTree;
import com.mabel.peer2peerLoans.lendertree.LendingNode;
import com.mabel.peer2peerLoans.lendertree.FillTree;;

public class Results {

	Input input = new Input();

	LendingNode root = Input.getRoot();
	
	//LendingInfoTree insertLendingInfo = new LendingInfoTree();
	FillTree insertLendingInfo = new FillTree();
	
	public void printResults() {
		LendingNode root = input.getRoot();
		System.out.println(root);
	
	//	public void printResults(int loanAmount) {
		System.out.println("Loan is " + input.getLoanAmount());
	
		FindingRate findingRate = new FindingRate();
		System.out.println(root);
		//findingRate.findRate(loanAmount, insertLendingInfo.root);
		findingRate.findRate(input.getLoanAmount(), root);
	
		
		System.out.println("Final Rate: " + findingRate.quotedRate(root));
		CalculatePayment calculatePayment = new CalculatePayment();
	}
	
	public Results() {
		printResults();

	}

	}
	

