package com.mabel.peer2peerLoans;

import java.io.File;

import com.mabel.peer2peerLoans.lendertree.LendingNode;

public class Input {
	
	public static int loanAmount;
	public static int termInMonths;
	public static String fileLocationName;
	public static File lenderFile;
	public static LendingNode root;
	
	public Input() {}
	
	public Input(int loanAmount, int termInMonths, String fileLocationName) {
		this.loanAmount = loanAmount;
		this.termInMonths = termInMonths;
		this.fileLocationName = fileLocationName;
	}
	
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getTermInMonths() {
		return termInMonths;
	}
	public void setTermInMonths(int termInMonths) {
		this.termInMonths = termInMonths;
	}
	public String getFileLocationName() {
		return fileLocationName;
	}
	public void setFileLocationName(String fileLocationName) {
		this.fileLocationName = fileLocationName;
	}
	public File getLenderFile() {
		return lenderFile;
	}
	public void setLenderFile(File lenderFile) {
		this.lenderFile = lenderFile;
	}

	public static LendingNode getRoot() {
		return root;
	}

	public static void setRoot(LendingNode root) {
		Input.root = root;
	}
}
