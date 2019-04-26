package com.mabel.peer2peerLoans.calculation;

import java.io.Console;

import com.mabel.peer2peerLoans.Input;
import com.mabel.peer2peerLoans.lendertree.FillTree;
import com.mabel.peer2peerLoans.lendertree.LendingInfoTree;
import com.mabel.peer2peerLoans.lendertree.LendingNode;

public class LowestRate {

	LendingInfoTree lendingInfoTree = new LendingInfoTree();
	FillTree readFile = new FillTree();
	
	
    public static Double findLowestRate(LendingNode root) {
    	
        if (root == null) 
        	return (double) -1; //tree is empty
        
        if (root.leftChild == null && root.lendingAmount > 0) {
        	return root.rate; //this is the minumum
        }
    	System.out.println(root.lendingAmount);

        System.out.println("Lowest Rate is " + root.leftChild);
        return findLowestRate(root.leftChild);
    }
    
    public static LendingNode findLowestRateNode(LendingNode root) {
    	LendingNode lendingNode;
    	if (root == null) {
        	System.out.println("We don't have any lenders");
            return lendingNode = null;

    	}
    	else if (root.leftChild == null) {
        	return root; //this is the minumum
        }
        return lendingNode = findLowestRateNode(root.leftChild);
    }
    
    /* Function to return least value */
    public Double findLowestRate() {
        //return findLowestRate(lendingInfoTree.root);    
        return findLowestRate(Input.getRoot());          

    }

}
