package com.mabel.peer2peerLoans.lendertree;

public class LendingNode {

	public String lenderName;
	public Double rate;
	public Integer lendingAmount;
	
	public LendingNode leftChild;
	public LendingNode rightChild;
	
	public LendingNode parent;
	
	LendingNode() {
	}
	
	LendingNode(String lenderName, Double rate, Integer lendingAmount){
		this.lenderName = lenderName;
		this.rate = rate;
		this.lendingAmount = lendingAmount;
	}
	
	public String toString() {
		return lenderName + " is willing to lend " + lendingAmount + " for " + rate*100 + " interest";
	}
}