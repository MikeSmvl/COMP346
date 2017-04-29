
/**
 * Class Account
 * Implements one customized customer account for many of our own threads.
 *
 * @author Malek Barhoush, mbarhoush@hotmail.com;
 * 
 *
 * $Revision: 1.0 $
 * $Last Revision Date: 2017/01/04
 */

public class Account {

	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * Attribute presents an account number
	 */
	
	private int acc;
	/**
	 * Attribute that presents an customer name
	 */
	private String name;
	/**
	 * Attribute that presents an account balance
	 */
	private double balance;
	
	/*
	 * ------------
	 * Constructors
	 * ------------
	 */

	/**
	 * Assigns account number, name and balance.
	 *
	 * @param acc A unique integer that represents account number
	 * @param name A string indicating human-readable customer's name
	 * @param balance A double indicating account balance
	 */

	public Account(int acc, String name, double balance) {
		super();
		this.acc = acc;
		this.name = name;
		this.balance = balance;
	}
	
	
	@Override
	/**
	 * equals method works as == operator 
	 * it checks if two accounts are identical
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (acc != other.acc)
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (acc!=other.acc)return false;
		return true;
	}

	/**
	 * Accessor for account no
	 * @return account no
	 */
	public int getAcc() {
		return acc;
	}
	
	/**
	 * Mutator for account no 
	 * @param acc A unique int for acoount number
	 */
	public void setAcc(int acc) {
		this.acc = acc;
	}
	
	/**
	 * Accessor for a customer's name
	 * @return a customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mutator for a customer name
	 * @param name A string that represents a customer name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor for account balance
	 * @return an account balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Mutator for account balance
	 * @param balance A double that represents an account balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * A method to print this account 
	 */
	public String toString(){
		return "Account: "+acc+" \tName: "+name+" \tBalance:\t"+balance;
	}

	/**
	 * A method that allows a customer to deposit money into this account
	 * @param amount A double that represents a deposit amount
	 */
	public void debosit(double amount){
		
		// Waste some time doing fake computations
		// do not remove or modify any of the following 3 statements
		double k = 999999999;
		for(int i=0;i<100;i++)
			k = k / 2;
		
		// Block level synchronization used
		synchronized (this) {
			balance = balance + amount;
		}
		
		// Waste some time doing fake computations
		// do not remove or modify any of the following 3 statements
		k = 999999999;
		for(int i=0;i<100;i++)
			k = k / 2;

	}

	/**
	 * A method that allows a customer to withdraw money from this account
	 * @param amount A double that represents a withdrawal amount
	 */
	public void withdraw(double amount){

		// Waste some time doing fake computations
		// do not remove or modify any of the following 3 statements
		double k = 999999999;
		for(int i=0;i<100;i++)
			k = k / 2;
		
		// Block level synchronization used
		synchronized (this) {
			balance = balance - amount;
		}
		
		// Waste some time doing fake computations
		// do not remove or modify any of the following 3 statements
		k = 999999999;
		for(int i=0;i<100;i++)
			k = k / 2;
	}
	

}
