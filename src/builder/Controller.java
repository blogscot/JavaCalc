package builder;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable{

	private float lastNumber = 0f;
	private float currentNumber = 0f;
	private float memory = 0f;
	private char lastOperation = ' ';
	private boolean startNewNumber = false;
	
	/**
	 * @return the displayed text as a Float
	 * 
	 */
	private float getCurrentNumber() {
		return Float.parseFloat(output.getText());
	}
	
	/**
	 * The displayed number is built up and displayed
	 * as the user issues keystrokes.
	 * 
	 * @param 	value the value key pressed by the user
	 */
	private void updateCurrentNumber(float value){
		// After the user presses an operator key start displaying a new number
		if (startNewNumber) {
			output.setText(Float.toString(value));
			startNewNumber = false;
		} else {
			output.setText(Float.toString(getCurrentNumber() * 10 + value));
		}
	}
	/**
	 * The user has pressed an operator key (i.e. +, - etc.)
	 * 
	 * @param c		operator key pressed
	 */
	private void processOperator(char c) {
		
		startNewNumber = true;
		
		if (lastOperation != ' ') {
			evaluateResult();
		} else {
			lastNumber = getCurrentNumber();
			lastOperation = c;
		}
	}

	/**
	 * 
	 * When the user presses '=' or an operator key 
	 * the current value is evaluated and displayed.
	 * 
	 */
	private void evaluateResult() {
		currentNumber = getCurrentNumber();
		
		switch (lastOperation){
		case '/':
			lastNumber /= currentNumber;
			break;
		case '*':
			lastNumber *= currentNumber;
			break;
		case '-':
			lastNumber -= currentNumber;
			break;
		case '+':
			lastNumber += currentNumber;
			break;
		default:
			lastNumber = 0;
			lastOperation = ' ';
		}
		output.setText(String.valueOf(lastNumber));
	}
	
	@FXML
	private Label output;
	
	public void one(){
		updateCurrentNumber(1);
	}
	public void two(){
		updateCurrentNumber(2);
	}
	public void three(){
		updateCurrentNumber(3);
	}
	public void four(){
		updateCurrentNumber(4);
	}
	public void five(){
		updateCurrentNumber(5);
	}
	public void six(){
		updateCurrentNumber(6);
	}
	public void seven(){
		updateCurrentNumber(7);
	}
	public void eight(){
		updateCurrentNumber(8);
	}
	public void nine(){
		updateCurrentNumber(9);
	}
	public void zero(){
		updateCurrentNumber(0);
	}
	public void memClear()
	{
		memory = 0f;
	}
	public void memPlus(){
		memory += getCurrentNumber();
	}
	public void memMinus(){
		memory -= getCurrentNumber();
	}
	public void memRecall(){
		output.setText(String.valueOf(memory));
	}
	public void plus(){
		processOperator('+');
	}
	public void minus(){
		processOperator('-');
	}
	public void divide(){
		processOperator('/');
	}
	public void multiply(){
		processOperator('*');
	}
	public void decimal(){
		// TODO
	}
	public void evaluate(){
		evaluateResult();
		startNewNumber = true;
		lastOperation = ' ';
	}
	public void reverseSign(){
		output.setText(String.valueOf(getCurrentNumber() * -1f));
	}
	public void clearAll(){
		output.setText("0.0");
		lastNumber = 0f;
		lastOperation = ' ';
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}