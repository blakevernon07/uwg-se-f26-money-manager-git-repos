package edu.westga.cs3211.moneymanager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	@CsvSource({ "100.0, 50.0", "500.0, 200.0", "1000.0, 750.0", "0.01, 0.01" })
	public void testConstructorValidValues(double income, double expenses) {

		Money money = new Money(income, expenses);

		assertEquals(income, money.getTotalIncome());
		assertEquals(expenses, money.getTotalExpenses());
	}

	/**
	 * Tests invalid income in the constructor.
	 *
	 * @param income the invalid income
	 */
	@ParameterizedTest
	@CsvSource({ "0.0", "-1.0", "-100.0" })
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
	@CsvSource({ "0.0", "-1.0", "-100.0" })
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
	@CsvSource({ "50.0", "100.0", "500.0", "1000.0" })
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
	@CsvSource({ "0.0", "-1.0", "-100.0" })
	public void testSetTotalIncomeInvalid(double income) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.setTotalIncome(income);
		});
	}

	/**
	 * Tests setting valid expenses.
	 *
	 * @param expenses the new expenses
	 */
	@ParameterizedTest
	@CsvSource({ "50.0", "100.0", "500.0", "1000.0" })
	public void testSetTotalExpensesValid(double expenses) {

		Money money = new Money(100.0, 50.0);

		money.setTotalExpenses(expenses);

		assertEquals(expenses, money.getTotalExpenses());
	}

	/**
	 * Tests setting invalid expenses.
	 *
	 * @param expenses the invalid expenses
	 */
	@ParameterizedTest
	@CsvSource({ "0.0", "-1.0", "-100.0" })
	public void testSetTotalExpensesInvalid(double expenses) {

		Money money = new Money(100.0, 50.0);

		assertThrows(IllegalArgumentException.class, () -> {
			money.setTotalExpenses(expenses);
		});
	}
}
