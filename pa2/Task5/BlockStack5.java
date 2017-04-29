/*
 * *********************************************************************************************************
 * Assignment 2 - COMP 346 SECTION YY
 * By Mikael Samvelian ID: 40003178
 * Due March 6, 2017
 * *********************************************************************************************************
 */

import common.MyException;

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
	private int accessCounter = 0;
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
	private int iSize = DEFAULT_SIZE; 

	/**
	 * Current top of the stack
	 */
	private int iTop = 3; 

	/**
	 * stack[0:5] with four defined values
	 */
	private char acStack[] = new char[] { 'a', 'b', 'c', 'd', '$', '$' }; 

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
	 * @return True if stack is empty, false otherwise
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
	 * @throws MyException 
	 */
	public char pick() throws MyException {
		accessCounter++;
		if ( !isEmpty()) {
			return this.acStack[this.iTop];
		} else {
			throw new MyException("The stack is empty.");
		}
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 * @throws MyException
	 */
	public char getAt(final int piPosition) throws MyException {
		accessCounter++;
		if ( piPosition <= iSize - 1) {
			return this.acStack[piPosition];
		} else {
			throw new MyException("Can't access position " + piPosition + " in the stack because it is greater then iSize which is " + iSize); 
		}
	}

	/**
	 * Standard push operation
	 * @throws Exception 
	 */
	public void push(final char pcBlock) throws MyException {
		accessCounter++;
		if ( iTop < iSize) {
			if(isEmpty()) {
				this.acStack[++this.iTop] = acStack[0];
			} else {
				this.acStack[++this.iTop] = pcBlock;
			}
		} else {
			throw new MyException("The stack is full.");
		}
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 * @throws MyException
	 */
	public char pop() throws MyException {
		accessCounter++;
		if (!isEmpty())
		{
			char cBlock = this.acStack[this.iTop];
			this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
			return cBlock;
		} else {
			throw new MyException("The stack is empty."); 
		}
	}
}

// EOF
