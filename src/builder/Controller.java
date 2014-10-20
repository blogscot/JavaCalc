package builder;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable {

	private double storedNumber = 0;
	private double currentNumber = 0;
	private int decimalPlace = 0;
	private double memory = 0;
	private static final char CLEAR = ' ';
	private char operatorKey = CLEAR;
	private boolean startNewNumber = true;

	private void displayNumber(double value) {
		output.setText(Double.toString(value));
	}

	/**
	 * The displayed number is built up and displayed as the user issues
	 * keystrokes.
	 * 
	 * @param value
	 *            the value key pressed by the user
	 */
	private void updateCurrentNumber(double value) {
		// After the user presses an operator key start displaying a new number
		if (startNewNumber) {
			currentNumber = value;
			startNewNumber = false;
			decimalPlace = 0;
		} else {
			if (decimalPlace == 0) {
				currentNumber = currentNumber * 10 + value;
			} else {
				currentNumber += value / Math.pow(10, decimalPlace);
				decimalPlace += 1;
			}
		}
		displayNumber(currentNumber);
	}

	/**
	 * The user has pressed an operator key (i.e. +, - etc.)
	 * 
	 * @param c
	 *            operator key pressed
	 */
	private void processOperator(char c) {

		startNewNumber = true;

		if (operatorKey == CLEAR) {
			// store current number and the operator pressed
			// in preparation for next number
			storedNumber = currentNumber;
			operatorKey = c;
		} else {
			// chaining operators (ie. 1+1+...)
			evaluateResult();
		}
	}

	/**
	 * 
	 * When the user presses '=' or an operator key the current value is
	 * evaluated and displayed.
	 * 
	 */
	private void evaluateResult() {

		switch (operatorKey) {
		case '/':
			storedNumber /= currentNumber;
			break;
		case '*':
			storedNumber *= currentNumber;
			break;
		case '-':
			storedNumber -= currentNumber;
			break;
		case '+':
			storedNumber += currentNumber;
			break;
		default:
			storedNumber = 0;
			operatorKey = CLEAR;
		}
		currentNumber = storedNumber;
		displayNumber(currentNumber);
	}

	@FXML
	private Label output;

	public void one() {
		updateCurrentNumber(1);
	}

	public void two() {
		updateCurrentNumber(2);
	}

	public void three() {
		updateCurrentNumber(3);
	}

	public void four() {
		updateCurrentNumber(4);
	}

	public void five() {
		updateCurrentNumber(5);
	}

	public void six() {
		updateCurrentNumber(6);
	}

	public void seven() {
		updateCurrentNumber(7);
	}

	public void eight() {
		updateCurrentNumber(8);
	}

	public void nine() {
		updateCurrentNumber(9);
	}

	public void zero() {
		updateCurrentNumber(0);
	}

	public void memClear() {
		memory = 0f;
	}

	public void memPlus() {
		memory += currentNumber;
	}

	public void memMinus() {
		memory -= currentNumber;
	}

	public void memRecall() {
		output.setText(String.valueOf(memory));
	}

	public void plus() {
		processOperator('+');
	}

	public void minus() {
		processOperator('-');
	}

	public void divide() {
		processOperator('/');
	}

	public void multiply() {
		processOperator('*');
	}

	public void decimal() {
		if (decimalPlace == 0){
			decimalPlace = 1;
		}
	}

	public void evaluate() {
		evaluateResult();
		startNewNumber = true;
		operatorKey = CLEAR;
	}

	public void reverseSign() {
		currentNumber *= -1;
		displayNumber(currentNumber);
	}

	public void clearAll() {
		output.setText("0.0");
		storedNumber = 0;
		decimalPlace = 0;
		operatorKey = CLEAR;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}