package edu.westga.cs3211.moneymanager.model;

/**
 * Defines the money for the money manager.
 * 
 * @author Blake Vernon
 * @version 11/11/26
 */
public class Money {

	private double totalIncome;
	private double totalExpenses;
	
	/**
	 * Creates the totalIncome and totalExpenses.
	 * 
	 * @precondition totalIncome !<= 0.
	 * @precondition totalExpenses !<= 0.
	 * @postcondition totalIncome and totalExpenses are created.
	 * 
	 * @param totalIncome The total amount of income for a user.
	 * @param totalExpenses The total amount of expenses for a user.
	 */
	public Money(double totalIncome, double totalExpenses) {
		if (totalIncome <= 0.0) {
			throw new IllegalArgumentException("totalIncome cannot be less than 0.");
		}
		if (totalExpenses <= 0.0) {
			throw new IllegalArgumentException("totalExpenses cannot be less than 0.");
		}
		
		this.totalIncome = totalIncome;
		this.totalExpenses = totalExpenses;
	}
}
