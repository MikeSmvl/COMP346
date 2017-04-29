/*
 * *********************************************************************************************************
 * Assignment 2 - COMP 346 SECTION YY
 * By Mikael Samvelian ID: 40003178
 * Due March 6, 2017
 * *********************************************************************************************************
 */

/**
 * Class BlockStack Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $ $Last Revision Date: 2017/02/08 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca; Inspired by an earlier
 *         code by Prof. D. Probst
 * 
 */
class BlockStack {
	/**
	 * Number of times the stack is accessed
	 */
	public int accessCounter = 0;
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	public int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	public int iTop = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	public char acStack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' };

	/**
	 * Accessor method for acStack
	 * @return the stack
	 */
	public char[] getAcStack() {
		return acStack;
	}

	/**
	 * Accessor method for accessCounter
	 * @return the amount of times the stack was accessed
	 */
	public int getAccessCounter() {
		return accessCounter;
	}

	/**
	 * Accessor method for ISize
	 * @return the size of the stack
	 */
	public int getISize() {
		return iSize;
	}

	/**
	 * Accessor method for ITop
	 * @return the current index for the top of the stack
	 */
	public int getITop() {
		return iTop;
	}

	/**
	 * Method to determine whether the stack is empty
	 * @return True if stack is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return (this.iTop == -1);
	}

	/**
	 * Default constructor
	 */
	public BlockStack() {
	}

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize) {

		if (piSize != DEFAULT_SIZE) {
			
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for (int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char) ('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick() {
		accessCounter++; // Increment the number of times stack is accessed
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition) {
		accessCounter++; // Increment the number of times stack is accessed
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock) {
		accessCounter++; // Increment the number of times stack is accessed
		this.acStack[++this.iTop] = pcBlock;
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop() {
		accessCounter++; // Increment the number of times stack is accessed
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
		return cBlock;
	}
}

// EOF
