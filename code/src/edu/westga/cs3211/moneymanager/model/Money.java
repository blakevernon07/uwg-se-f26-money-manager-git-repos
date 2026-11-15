package edu.westga.cs3211.moneymanager.model;

import java.util.ArrayList;

/**
 * Defines the money for the money manager.
 * 
 * @author Blake Vernon
 * @version 11/11/26
 */
public class Money {

	private double totalIncome;
	private double totalExpense;
	private ArrayList<String> expenses;

	/**
	 * Creates the totalIncome and totalExpenses.
	 * 
	 * @precondition totalIncome !< 0.0.
	 * @precondition totalExpense !< 0.0.
	 * @postcondition totalIncome and expenses are created.
	 * 
	 * @param totalIncome  The total amount of income for a user.
	 * @param totalExpense The total expense the user enters.
	 */
	public Money(double totalIncome, double totalExpense) {
		if (totalIncome < 0.0) {
			throw new IllegalArgumentException("totalIncome cannot be less than 0.");
		}
		if (totalExpense < 0.0) {
			throw new IllegalArgumentException("totalExpense cannot be less than 0.");
		}

		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
		this.expenses = new ArrayList<String>();
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
	 * Gets the totalExpense.
	 * 
	 * @return double The expense to return.
	 */
	public double getTotalExpense() {
		return this.totalExpense;
	}

	/**
	 * Gets the list of expenses() {
	 * 
	 * @return ArrayList<String> The list of expenses.
	 */
	public ArrayList<String> getExpenses() {
		return this.expenses;
	}

	/**
	 * Sets the totalIncome.
	 * 
	 * @precondition none
	 * @postcondition sets the new value for the totalIncome.
	 * 
	 * @param newTotalIncome The new value.
	 */
	public void setTotalIncome(double newTotalIncome) {
		this.totalIncome = newTotalIncome;
	}

	/**
	 * Sets the totalExpense.
	 * 
	 * @precondition newTotalExpense !< 0.0
	 * @postcondition sets the new value for the totalExpense.
	 * 
	 * @param newTotalExpense The new value.
	 */
	public void setTotalExpense(double newTotalExpense) {
		if (newTotalExpense < 0.0) {
			throw new IllegalArgumentException("The new total expense cannot be less than 0.");
		}

		this.totalExpense = newTotalExpense;
	}

	/**
	 * Adds an expense to the list.
	 * 
	 * @precondition newExpense !< 0.0.
	 * @precondition !reasonValue.isEmpty().
	 * @precondition reasonValue != null.
	 * @postcondition Adds a new value to the list of expenses.
	 * 
	 * @param newExpense  The new value.
	 * @param reasonValue The reason for the income/expense.
	 * @param isIncome    If the value is expense or income.
	 */
	public void addExpense(double newExpense, String reasonValue, boolean isIncome) {
		if (newExpense < 0.0) {
			throw new IllegalArgumentException("The new expense cannot be less than 0.");
		}
		if (reasonValue == null) {
			throw new IllegalArgumentException("reasonValue cannot be null.");
		}
		if (reasonValue.isEmpty()) {
			throw new IllegalArgumentException("reasonValue cannot be empty.");
		}

		if (isIncome) {
			String value = String.format("$%.2f", newExpense);
			String newValue = "+ " + value;
			newValue += " - Reason: " + reasonValue;
			this.expenses.add(newValue);
		} else {
			String value = String.format("$%.2f", newExpense);
			String newValue = "- " + value;
			newValue += " - Reason: " + reasonValue;
			this.expenses.add(newValue);
		}
	}
}
