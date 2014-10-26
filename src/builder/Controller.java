package builder;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable {

	private String currentNumber = "";
	private double memory = 0;
	private double storedNumber = 0;
	private static final char EMPTY = ' ';
	private char storedKey = EMPTY;
	private boolean startNewNumber = true;
	private boolean hasDecimal = false;
	private boolean equalsPressed = false;

	private void displayNumber(String value) {
		output.setText(value);
	}

	private void displayNumber(Double value) {
		output.setText(String.valueOf(value));
	}

	/**
	 * The displayed number is built up and displayed as the user issues
	 * keystrokes.
	 * 
	 * @param key
	 *            the digit or decimal pressed by the user
	 */
	private void updateCurrentKey(String key) {
		// At the beginning of a new calculation or after the user presses
		// an operator key start displaying a new number

		// we've started a new number so clear flag
		equalsPressed = false;

		if (startNewNumber) {
			if (key == ".") {
				// User pressed decimal key first
				// Append number after decimal
				currentNumber = "0.";
				hasDecimal = true;
			} else {
				// user presses a digit first
				currentNumber = key;
			}
			startNewNumber = false;
		} else {
			currentNumber += key;
		}
		displayNumber(currentNumber);
	}

	/**
	 * The user has pressed an operator key (i.e. +, - etc.)
	 * 
	 * @param c
	 *            operator key pressed
	 */
	private void processOperator(char operator) {

		// Start a new number whenever an operator is pressed
		startNewNumber = true;
		hasDecimal = false;

		if (storedKey == EMPTY) {
			// first operator pressed i.e. 1+
			// store current number and the operator pressed
			// in preparation for next number
			storedNumber = Double.parseDouble(currentNumber);
			storedKey = operator;
		} else {
			// Note: a calculator has the behaviour if the previous
			// key press was '=' (instead of a digit), pressing
			// an operator key (+,-,/,*) does not cause a new evaluate
			if (equalsPressed) {
				storedKey = operator;
			} else {
			// chaining operators (i.e. 1+1+...)
			evaluateResult();
			storedKey = operator; }
		}
		equalsPressed = false;
	}

	/**
	 * When the user presses '=' or an operator key the current expression is
	 * evaluated and displayed.
	 * 
	 * @param storedNumber
	 *            the running expression value
	 * @param currentNumber
	 *            the latest user number
	 * @param storedKey
	 *            the user selected operation
	 */
	private void evaluateResult() {

		double number = Double.parseDouble(currentNumber);

		switch (storedKey) {
		case '/':
			storedNumber /= number;
			break;
		case '*':
			storedNumber *= number;
			break;
		case '-':
			storedNumber -= number;
			break;
		case '+':
			storedNumber += number;
			break;
		default:
			// When the '=' key is repeatedly pressed repeat
			// the last operator against the stored value.
		}
		displayNumber(storedNumber);
	}

	public void evaluate() {
		equalsPressed = true;
		evaluateResult();
		// reset number parsing
		startNewNumber = true;
		hasDecimal = false;
	}

	// It is possible to reverse the sign of the calculated
	// expression or the current value being typed. 
	public void reverseSign() {
		
		if (equalsPressed) {
			// storedNumber is displayed
			storedNumber = storedNumber * -1;
			displayNumber(storedNumber);
		} else {
			// currentNumber is displayed
			currentNumber = String.valueOf(Double.parseDouble(currentNumber) * -1);
			displayNumber(currentNumber);
		}
	}

	public void clearAll() {
		// clear display and stored values and flags
		output.setText("0.0");
		storedNumber = 0;
		currentNumber = "";
		storedKey = EMPTY;
		equalsPressed = true;
		// reset number parsing
		startNewNumber = true;
		hasDecimal = false;
	}

	@FXML
	private Label output;

	public void one() {
		updateCurrentKey("1");
	}

	public void two() {
		updateCurrentKey("2");
	}

	public void three() {
		updateCurrentKey("3");
	}

	public void four() {
		updateCurrentKey("4");
	}

	public void five() {
		updateCurrentKey("5");
	}

	public void six() {
		updateCurrentKey("6");
	}

	public void seven() {
		updateCurrentKey("7");
	}

	public void eight() {
		updateCurrentKey("8");
	}

	public void nine() {
		updateCurrentKey("9");
	}

	public void zero() {
		updateCurrentKey("0");
	}

	public void decimal() {
		if (!hasDecimal) {
			hasDecimal = true;
			updateCurrentKey(".");
		}
	}

	public void memClear() {
		memory = 0f;
	}

	public void memPlus() {
		
		if (equalsPressed) {
			// storedNumber is displayed
			memory += storedNumber;
		} else {
			// currentNumber is displayed
			memory += Double.parseDouble(currentNumber);
		}
	}

	public void memMinus() {
		
		if (equalsPressed) {
			// storedNumber is displayed
			memory -= storedNumber;
		} else {
			// currentNumber is displayed
			memory -= Double.parseDouble(currentNumber);
		}
	}

	public void memRecall() {
		currentNumber = String.valueOf(memory);
		displayNumber(memory);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}