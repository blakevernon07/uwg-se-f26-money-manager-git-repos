package edu.westga.cs3211.moneymanager.controller;

import javafx.fxml.FXML;
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
	private Label currentIncomeTextField;

	@FXML
	private Button expenseButton;

	@FXML
	private Label expenseListLabel;

	@FXML
	private ListView<?> expenseListView;

	@FXML
	private TextField expensesTextField;

	@FXML
	private Button incomeButton;

	@FXML
	private TextField incomeTextField;

	@FXML
	private Label leftOverTextField;
}
