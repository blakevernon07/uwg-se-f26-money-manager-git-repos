
package edu.westga.cs3211.moneymanager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.cs3211.moneymanager.model.Money;

public class TestWhenCreateMoney {

	/**
	 * Tests the constructor with valid values.
	 *
	 * @param income   the income
	 * @param expenses the expenses
	 */
	@ParameterizedTest
	@CsvSource({ "0.0, 0.0", "100.0, 50.0", "500.0, 200.0", "1000.0, 750.0", "0.01, 0.01" })
	public void testConstructorValidValues(double income, double expenses) {

		Money money = new Money(income, expenses);

		assertEquals(income, money.getTotalIncome());
		assertEquals(expenses, money.getTotalExpense());
		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests invalid income in the constructor.
	 *
	 * @param income the invalid income
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01", "-1.0", "-100.0" })
	public void testConstructorInvalidIncome(double income) {

		assertThrows(IllegalArgumentException.class, () -> {
			new Money(income, 100.0);
		});
	}

	/**
	 * Tests invalid expenses in the constructor.
	 *
	 * @param expenses the invalid expenses
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01", "-1.0", "-100.0" })
	public void testConstructorInvalidExpenses(double expenses) {

		assertThrows(IllegalArgumentException.class, () -> {
			new Money(100.0, expenses);
		});
	}

	/**
	 * Tests setting valid income.
	 *
	 * @param income the new income
	 */
	@ParameterizedTest
	@CsvSource({ "0.0", "50.0", "100.0", "500.0", "1000.0" })
	public void testSetTotalIncomeValid(double income) {

		Money money = new Money(100.0, 50.0);

		money.setTotalIncome(income);

		assertEquals(income, money.getTotalIncome());
	}

	/**
	 * Tests setting invalid income.
	 *
	 * @param income the invalid income
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01", "-1.0", "-100.0" })
	public void testSetTotalIncomeInvalid(double income) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.setTotalIncome(income);
		});

		assertEquals(100.0, money.getTotalIncome());
	}

	/**
	 * Tests setting valid expenses.
	 *
	 * @param expenses the new expenses
	 */
	@ParameterizedTest
	@CsvSource({ "0.0", "50.0", "100.0", "500.0", "1000.0" })
	public void testSetTotalExpensesValid(double expenses) {

		Money money = new Money(100.0, 50.0);

		money.setTotalExpense(expenses);

		assertEquals(expenses, money.getTotalExpense());
	}

	/**
	 * Tests setting invalid expenses.
	 *
	 * @param expenses the invalid expenses
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01", "-1.0", "-100.0" })
	public void testSetTotalExpensesInvalid(double expenses) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.setTotalExpense(expenses);
		});

		assertEquals(50.0, money.getTotalExpense());
	}

	/**
	 * Tests that the expense list starts empty.
	 */
	@Test
	public void testGetExpensesInitiallyEmpty() {

		Money money = new Money(100.0, 50.0);

		assertTrue(money.getExpenses().isEmpty());
		assertEquals(0, money.getExpenses().size());
	}

	/**
	 * Tests adding valid expenses.
	 *
	 * @param expense  the expense amount
	 * @param reason   the expense reason
	 * @param expected the expected formatted string
	 */
	@ParameterizedTest
	@CsvSource({ "0.0, Food, '$0.00 - Reason: Food'", "10.0, Gas, '$10.00 - Reason: Gas'",
			"25.50, Shopping, '$25.50 - Reason: Shopping'", "100.0, Bills, '$100.00 - Reason: Bills'",
			"0.01, Snacks, '$0.01 - Reason: Snacks'" })
	public void testAddExpenseValid(double expense, String reason, String expected) {

		Money money = new Money(100.0, 50.0);

		money.addExpense(expense, reason);

		assertEquals(1, money.getExpenses().size());
		assertEquals(expected, money.getExpenses().get(0));
	}

	/**
	 * Tests adding a negative expense.
	 *
	 * @param expense the invalid expense
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01", "-1.0", "-100.0" })
	public void testAddExpenseNegative(double expense) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(expense, "Food");
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding an expense with a null reason.
	 */
	@Test
	public void testAddExpenseNullReason() {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(25.0, null);
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding an expense with an empty reason.
	 */
	@Test
	public void testAddExpenseEmptyReason() {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(25.0, "");
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding multiple expenses to the list.
	 */
	@Test
	public void testAddMultipleExpenses() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(25.0, "Food");
		money.addExpense(40.0, "Gas");
		money.addExpense(15.50, "Shopping");

		assertEquals(3, money.getExpenses().size());

		assertEquals("$25.00 - Reason: Food", money.getExpenses().get(0));

		assertEquals("$40.00 - Reason: Gas", money.getExpenses().get(1));

		assertEquals("$15.50 - Reason: Shopping", money.getExpenses().get(2));
	}

	/**
	 * Tests that adding an expense does not change the total income or total
	 * expense.
	 */
	@Test
	public void testAddExpenseDoesNotChangeTotals() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(25.0, "Food");

		assertEquals(500.0, money.getTotalIncome());
		assertEquals(100.0, money.getTotalExpense());
	}
}
