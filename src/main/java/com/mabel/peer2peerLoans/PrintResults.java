package com.mabel.peer2peerLoans;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.mabel.peer2peerLoans.calculation.CalculatePayment;
import com.mabel.peer2peerLoans.calculation.FindingRate;
import com.mabel.peer2peerLoans.lendertree.FillTree;
import com.mabel.peer2peerLoans.lendertree.LendingNode;

public class PrintResults {
	
	//LendingInfoTree insertLendingInfo = new LendingInfoTree();
	FillTree insertLendingInfo = new FillTree();
	
	Input input = new Input();
	LendingNode root = input.getRoot();
	
	public PrintResults() {

		//LendingNode root = insertLendingInfo.uploadFile().root;
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
}
