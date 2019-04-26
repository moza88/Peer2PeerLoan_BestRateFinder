package com.mabel.peer2peerLoans;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;

import com.mabel.peer2peerLoans.calculation.CalculatePayment;
import com.mabel.peer2peerLoans.calculation.FindingRate;
import com.mabel.peer2peerLoans.lendertree.FillTree;
import com.mabel.peer2peerLoans.lendertree.LendingNode;

public class PrintResults {
	
	//LendingInfoTree insertLendingInfo = new LendingInfoTree();
	FillTree insertLendingInfo = new FillTree();
	
	Input input = new Input();
	LendingNode root = Input.getRoot();
	
	public PrintResults() {
	
		FindingRate findingRate = new FindingRate();
		findingRate.findRate(input.getLoanAmount(), root);
		
		CalculatePayment calculatePayment = new CalculatePayment();
		double monthlyPayment = calculatePayment.monthlyPayment(findingRate.quotedRate(root), input.getTermInMonths(), input.getLoanAmount());
		double totalRepayment = calculatePayment.totalRepayment(monthlyPayment, input.getTermInMonths(), input.getLoanAmount());
		
		System.out.println("Requested amount: " + input.getLoanAmount());

		System.out.println("Rate: " + Precision.round(findingRate.quotedRate(root)*100, 1));
		
		System.out.println("Monthly repayment: " + Precision.round(monthlyPayment, 2));
				
		System.out.println("Total repayment: " + Precision.round(totalRepayment, 2));
	}
}
