/**
*Description: This program Defines a dice object and its methods
*Date: 02/27/2023
*@version 0.0.0
*/ 
import java.util.Random;

public class Dice {
	private int sides;
	private int value;


/**
* @param String as args
* @return Termination code as int, 0 for normal, anything else is error condition
* @throws Nothing is implemented
*/
	public Dice(int numSides) {
		this.sides = numSides;
		roll();
	}

/**
* @param String as args
* @return Termination code as int, 0 for normal, anything else is error condition
* @throws Nothing is implemented
*/
	public void roll() {
		Random rand = new Random();

		this.value = rand.nextInt(sides) + 1;
	}

/**
* @param String as args
* @return Termination code as int, 0 for normal, anything else is error condition
* @throws Nothing is implemented
*/
	public int getValue() {
		return this.value;
	}
}
