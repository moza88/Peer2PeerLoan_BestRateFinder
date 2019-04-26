package com.mabel.peer2peerLoans.lendertree;

import com.mabel.peer2peerLoans.calculation.LowestRate;

import com.mabel.peer2peerLoans.lendertree.LendingNode;

public class Remove {

	// Function to delete LendingNode from a BST
	public static LendingNode deleteLendingNode(LendingNode root, Double rate, String lenderName)
	{
		// pointer to store parent LendingNode of current LendingNode
		LendingNode parent = null;

		// start with root LendingNode
		LendingNode focusNode = root;

		// search key in BST and set its parent pointer
		while (focusNode != null && focusNode.rate != rate && focusNode.lenderName != lenderName)
		{
			// update parent LendingNode as current LendingNode
			parent = focusNode;

			// if given key is less than the current LendingNode, go to left subtree
			// else go to right subtree
			if (rate < focusNode.rate) {
				focusNode = focusNode.leftChild;
			}
			else {
				focusNode = focusNode.rightChild;
			}
		}

		// return if key is not found in the tree
		if (focusNode == null) {
			return root;
		}

		//LendingNode to be deleted has no children 
		if (focusNode.leftChild == null && focusNode.rightChild == null)
		{
			// if LendingNode to be deleted is not a root LendingNode, then set its
			// parent left/right child to null
			if (focusNode != root) {
				if (parent.leftChild == focusNode) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			}
			// if tree has only root LendingNode, delete it and set root to null
			else {
				root = null;
			}
		}

		//LendingNode to be deleted has two children
		else if (focusNode.leftChild != null && focusNode.rightChild != null)
		{
			LowestRate lowestRate = new LowestRate();
			// find its in-order successor LendingNode
			LendingNode successor  = LowestRate.findLowestRateNode((focusNode.rightChild));

			// store successor value
			Double val = successor.rate;

			// recursively delete the successor. Note that the successor
			// will have at-most one child (right child)
			deleteLendingNode(root, successor.rate, successor.lenderName);

			// Copy the value of successor to current LendingNode
			focusNode.rate = val;
		}

		//LendingNode to be deleted has only one child
		else
		{
			// find child LendingNode
			LendingNode child = (focusNode.leftChild != null)? focusNode.leftChild: focusNode.rightChild;

			// if LendingNode to be deleted is not a root LendingNode, then set its parent
			// to its child
			if (focusNode != root)
			{
				if (focusNode == parent.leftChild) {
					parent.leftChild = child;
				} else {
					parent.rightChild = child;
				}
			}

			// if LendingNode to be deleted is root LendingNode, then set the root to child
			else {
				root = child;
			}
		}

		return root;
	}
}
