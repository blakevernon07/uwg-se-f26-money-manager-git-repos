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
	 * @precondition totalIncome !<= 0.0.
	 * @precondition totalExpenses !<= 0.0.
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
	
	/**
	 * Gets the totalIncome.
	 * 
	 * @return double The totalIncome.
	 */
	public double getTotalIncome() {
		return this.totalIncome;
	}
	
	/**
	 * Gets the totalExpenses() {
	 * 
	 * @return double The totalExpenses.
	 */
	public double getTotalExpenses() {
		return this.totalExpenses;
	}
	
	/**
	 * Sets the totalIncome.
	 * 
	 * @precondition totalIncome !<= 0.0.
	 * @postcondition sets the new value for the totalIncome.
	 * 
	 * @param newTotalIncome The new value.
	 */
	public void setTotalIncome(double newTotalIncome) {
		if (newTotalIncome <= 0.0) {
			throw new IllegalArgumentException("The new total income cannot be less than 0.");
		}
		
		this.totalIncome = newTotalIncome;
	}
	
	/**
	 * Sets the totalExpenses.
	 * 
	 * @precondition totalExpenses !<= 0.0.
	 * @postcondition sets the new value for the totalExpenses.
	 * 
	 * @param newTotalExpenses The new value.
	 */
	public void setTotalExpenses(double newTotalExpenses) {
		if (newTotalExpenses <= 0.0) {
			throw new IllegalArgumentException("The new total expenses cannot be less than 0.");
		}
		
		this.totalExpenses = newTotalExpenses;
	}
}
