package builder;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable{

	private float lastNumber;
	private float currentNumber;
	private char lastOperation;
	private boolean waitingOperator = false;
	
	private float getCurrentNumber() {
		return Float.parseFloat(output.getText());
	}
	
	private void updateCurrentNumber(float value){
		if (getCurrentNumber() == 0f || waitingOperator) {
			output.setText(Float.toString(value));
			waitingOperator = false;
		} else {
			output.setText(Float.toString(getCurrentNumber() * 10 + value));
		}
	}
	
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
			output.setText("NaN");
		}
		// following evaluation users can chain operators but not digits
		waitingOperator = true;
		lastOperation = ' ';
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
	public void plus(){
		if (lastOperation != ' ') {
			evaluateResult();
		} else {
			lastNumber = getCurrentNumber();
			output.setText("0");
			lastOperation = '+';
		}
	}
	public void minus(){
		if (lastOperation != ' ') {
			evaluateResult();
		} else {
			lastNumber = getCurrentNumber();
			output.setText("0");
			lastOperation = '-';
		}
	}
	public void divide(){
		if (lastOperation != ' ') {
			evaluateResult();
		} else {
			lastNumber = getCurrentNumber();
			output.setText("0");
			lastOperation = '/';
		}

	}
	public void multiply(){
		if (lastOperation != ' ') {
			evaluateResult();
		} else {
			lastNumber = getCurrentNumber();
			output.setText("0");
			lastOperation = '*';
		}

	}
	public void decimal(){
		// TODO
	}
	public void evaluate(){
		evaluateResult();
	}
	public void reverseSign(){
		output.setText(String.valueOf(getCurrentNumber() * -1f));
	}
	public void clearAll(){
		output.setText("0");
		lastNumber = 0f;
		lastOperation = ' ';
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}

}