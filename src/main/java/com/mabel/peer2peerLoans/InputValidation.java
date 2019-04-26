package com.mabel.peer2peerLoans;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.mabel.peer2peerLoans.lendertree.FillTree;

public class InputValidation {

	static Scanner scanner = new Scanner(System.in);

	Constants constants = new Constants();
	FillTree fillTree = new FillTree();
	
	Input input = new Input();
	
	public void loanAmountValidation() {
		if(input.getLoanAmount() < constants.MIN_LOAN_AMOUNT) {
			System.out.println("Please enter a loan larger than or equal to " + constants.MIN_LOAN_AMOUNT);
			enterLoanAmount();
		}else if(input.getLoanAmount() % constants.LOAN_INCREMENT != 0) {
			System.out.println("We only accept loans in " + constants.LOAN_INCREMENT + " increments");
			enterLoanAmount();
		}
		
		if(input.getLoanAmount() > constants.MAX_LOAN_AMOUNT) {
			System.out.println("Please enter a loan less than or equal to " + constants.MAX_LOAN_AMOUNT);
			enterLoanAmount();

		}else if(input.getLoanAmount() % constants.LOAN_INCREMENT != 0) {
			System.out.println("We only accept loans in " + constants.LOAN_INCREMENT + " increments");
			enterLoanAmount();
		}
		
		if(input.getLoanAmount() % constants.LOAN_INCREMENT != 0) {
			System.out.println("We only accept loans in " + constants.LOAN_INCREMENT + " increments");
			enterLoanAmount();
		}
	}
	
	public void enterLoanAmount() {
		System.out.print("Enter loan amount requested:");
		try {
			input.setLoanAmount(scanner.nextInt());
		}catch (InputMismatchException e) {
			System.out.println("Please enter a number");
			System.out.println("Restart the program");
		}
	}
	

	public void enterFileLocationName() {
		System.out.println("Enter file location and file name of lenders: ");
		input.setFileLocationName(scanner.next());
	}
	
	public boolean doesFileExist(File lenderFile) {
		if(!lenderFile.exists()) {
			System.out.println("Lender file does not exist");	
		}
		return false;
	}
	
	public InputValidation() {

		enterLoanAmount();
		
		loanAmountValidation();

		enterFileLocationName();
		
		if(new File(input.getFileLocationName()).exists()) {
			System.out.println("Perfect! Processing your " + input.getLoanAmount() + " from " + input.getFileLocationName());
			try {
				fillTree.uploadFile(input.getFileLocationName(), input.getLoanAmount());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("File doesn't exist please re-enter name");
			enterFileLocationName();
		}

	}
}
