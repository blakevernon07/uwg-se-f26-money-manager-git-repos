
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
	 * Tests adding valid expense entries.
	 *
	 * @param expense  the expense amount
	 * @param reason   the reason
	 * @param expected the expected String
	 */
	@ParameterizedTest
	@CsvSource({ "0.0, Food, '- $0.00 - Reason: Food'", "10.0, Gas, '- $10.00 - Reason: Gas'",
			"25.50, Shopping, '- $25.50 - Reason: Shopping'", "100.0, Bills, '- $100.00 - Reason: Bills'",
			"0.01, Snacks, '- $0.01 - Reason: Snacks'" })
	public void testAddExpenseValid(double expense, String reason, String expected) {

		Money money = new Money(100.0, 50.0);

		money.addExpense(expense, reason, false);

		assertEquals(1, money.getExpenses().size());
		assertEquals(expected, money.getExpenses().get(0));
	}

	/**
	 * Tests adding valid income entries.
	 *
	 * @param income   the income amount
	 * @param reason   the reason
	 * @param expected the expected String
	 */
	@ParameterizedTest
	@CsvSource({ "0.0, Gift, '+ $0.00 - Reason: Gift'", "10.0, Tips, '+ $10.00 - Reason: Tips'",
			"25.50, Bonus, '+ $25.50 - Reason: Bonus'", "100.0, Work, '+ $100.00 - Reason: Work'",
			"500.0, Paycheck, '+ $500.00 - Reason: Paycheck'" })
	public void testAddIncomeValid(double income, String reason, String expected) {

		Money money = new Money(100.0, 50.0);

		money.addExpense(income, reason, true);

		assertEquals(1, money.getExpenses().size());
		assertEquals(expected, money.getExpenses().get(0));
	}

	/**
	 * Tests adding a negative expense or income.
	 *
	 * @param expense  the invalid amount
	 * @param isIncome whether the entry is income
	 */
	@ParameterizedTest
	@CsvSource({ "-0.01, true", "-1.0, true", "-100.0, true", "-0.01, false", "-1.0, false", "-100.0, false" })
	public void testAddExpenseNegative(double expense, boolean isIncome) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(expense, "Food", isIncome);
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding an entry with a null reason.
	 *
	 * @param isIncome whether the entry is income
	 */
	@ParameterizedTest
	@CsvSource({ "true", "false" })
	public void testAddExpenseNullReason(boolean isIncome) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(25.0, null, isIncome);
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding an entry with an empty reason.
	 *
	 * @param isIncome whether the entry is income
	 */
	@ParameterizedTest
	@CsvSource({ "true", "false" })
	public void testAddExpenseEmptyReason(boolean isIncome) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.addExpense(25.0, "", isIncome);
		});

		assertTrue(money.getExpenses().isEmpty());
	}

	/**
	 * Tests adding multiple expense entries.
	 */
	@Test
	public void testAddMultipleExpenses() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(25.0, "Food", false);
		money.addExpense(40.0, "Gas", false);
		money.addExpense(15.50, "Shopping", false);

		assertEquals(3, money.getExpenses().size());

		assertEquals("- $25.00 - Reason: Food", money.getExpenses().get(0));

		assertEquals("- $40.00 - Reason: Gas", money.getExpenses().get(1));

		assertEquals("- $15.50 - Reason: Shopping", money.getExpenses().get(2));
	}

	/**
	 * Tests adding multiple income entries.
	 */
	@Test
	public void testAddMultipleIncomeEntries() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(250.0, "Paycheck", true);
		money.addExpense(50.0, "Gift", true);
		money.addExpense(25.0, "Tips", true);

		assertEquals(3, money.getExpenses().size());

		assertEquals("+ $250.00 - Reason: Paycheck", money.getExpenses().get(0));

		assertEquals("+ $50.00 - Reason: Gift", money.getExpenses().get(1));

		assertEquals("+ $25.00 - Reason: Tips", money.getExpenses().get(2));
	}

	/**
	 * Tests adding both income and expenses to the same list.
	 */
	@Test
	public void testAddIncomeAndExpenseTogether() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(250.0, "Paycheck", true);
		money.addExpense(40.0, "Gas", false);
		money.addExpense(50.0, "Gift", true);
		money.addExpense(25.0, "Food", false);

		assertEquals(4, money.getExpenses().size());

		assertEquals("+ $250.00 - Reason: Paycheck", money.getExpenses().get(0));

		assertEquals("- $40.00 - Reason: Gas", money.getExpenses().get(1));

		assertEquals("+ $50.00 - Reason: Gift", money.getExpenses().get(2));

		assertEquals("- $25.00 - Reason: Food", money.getExpenses().get(3));
	}

	/**
	 * Tests that adding an entry does not automatically change the totals.
	 */
	@Test
	public void testAddExpenseDoesNotChangeTotals() {

		Money money = new Money(500.0, 100.0);

		money.addExpense(25.0, "Food", false);
		money.addExpense(100.0, "Work", true);

		assertEquals(500.0, money.getTotalIncome());
		assertEquals(100.0, money.getTotalExpense());
	}
}
