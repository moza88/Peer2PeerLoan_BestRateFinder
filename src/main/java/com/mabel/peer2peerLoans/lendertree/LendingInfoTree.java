package com.mabel.peer2peerLoans.lendertree;

import com.mabel.peer2peerLoans.Input;

public class LendingInfoTree {
	
	//public LendingNode root;
	
	public LendingNode parent;
	
	Input input = new Input();
	//public LendingNode root = Input.getRoot();
	
	public void insert(String lenderName, Double rate, Integer lendingAmount) {
		
		
		LendingNode newNode = new LendingNode(lenderName, rate, lendingAmount);
		
		if(Input.getRoot() == null) {
			Input.setRoot(newNode);

		}else {
			//Set root as the Node we'll start from
			LendingNode focusNode = Input.getRoot();
			
			while(true) {
				
				//let's start from the top root
				parent = focusNode;
				
				//if the rate is lower than the current node then focus on the left child
				if(rate < focusNode.rate) {
					focusNode = focusNode.leftChild;
					
					//If the left child has no children
					if(focusNode == null) {
						//Then place the new node to the left of it
						parent.leftChild = newNode;
						return; //Now you have placed the new node in the proper spot
					}
				} else {
					//if the rate is higher than the current node then focus on the right child
					focusNode = focusNode.rightChild;
					
					//if the right child has no children
					if(focusNode == null) {
						//then place the new node to the right of it
						parent.rightChild = newNode;
						return;  //Now you have placed the new node in the proper spot
					}
					
				}
			}
				
		}
	}
}
