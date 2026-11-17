package edu.westga.cs3211.moneymanager.controller;

import edu.westga.cs3211.moneymanager.model.Money;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Defines the JavaFX elements of a GUI.
 * 
 * @author Blake Vernon
 * @version 11/11/26
 */
public class MainController {

	@FXML
	private Label balanceTextField;

	@FXML
	private Button expenseButton;

	@FXML
	private Label logListLabel;

	@FXML
	private ListView<String> logListView;

	@FXML
	private TextField expensesTextField;

	@FXML
	private Button incomeButton;

	@FXML
	private TextField incomeTextField;

	@FXML
	private TextField reasonTextField;

	private Money myMoney = new Money(0.0, 0.0);

	@FXML
	private void handleAddIncome() {

		try {
			double income = Double.parseDouble(this.incomeTextField.getText());

			this.myMoney.addExpense(income, this.reasonTextField.getText(), true);

			this.logListView.setItems(FXCollections.observableArrayList(this.myMoney.getExpenses()));

			this.myMoney.setTotalIncome(this.myMoney.getTotalIncome() + income);

			this.balanceTextField.setText(String.format("Balance: $%.2f", this.myMoney.getTotalIncome()));

		} catch (IllegalArgumentException myException) {
			Alert myAlert = new Alert(AlertType.ERROR);
			myAlert.setTitle("Invalid Input");
			myAlert.setHeaderText("Invalid Income/Reason");
			myAlert.setContentText("Please enter a valid income/reason.");
			myAlert.showAndWait();
		}
	}

	@FXML
	private void handleAddExpense() {

		try {
			double expense = Double.parseDouble(this.expensesTextField.getText());

			this.myMoney.addExpense(expense, this.reasonTextField.getText(), false);

			this.myMoney.setTotalExpense(expense);

			this.logListView.setItems(FXCollections.observableArrayList(this.myMoney.getExpenses()));

			double newBalance = this.myMoney.getTotalIncome() - this.myMoney.getTotalExpense();
			this.myMoney.setTotalIncome(newBalance);

			this.balanceTextField.setText(String.format("Balance: $%.2f", newBalance));

		} catch (IllegalArgumentException myException) {
			Alert myAlert = new Alert(AlertType.ERROR);
			myAlert.setTitle("Invalid Input");
			myAlert.setHeaderText("Invalid Expense/Reason");
			myAlert.setContentText("Please enter a valid expense/reason.");
			myAlert.showAndWait();
		}
	}
}
