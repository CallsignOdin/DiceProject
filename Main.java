/**
*Description: This program Drives a blackjack game
*Date: 02/27/2023
*@version 0.0.0
*/

//Time to add an input reader class
import java.io.*;


public class Main {
	public static InputReader inputReader = new InputReader();

	public static void main(String[] args) throws IOException {
	//Replacing Scanner declaration with InputReader
	 
		/*
		//Stuff from school, not necessary

		File welcomeMsgFile = new File("input.txt");
		Scanner inputFile = new Scanner(welcomeMsgFile);

		String welcomeMsg = inputFile.nextLine();
		System.out.println(welcomeMsg);
		System.out.println();
		inputFile.close();
		*/

		

		//starts an instance of Dice blackjack
		//Passing InputReader obj to Game obj
		Game game = new Game(inputReader);
	}
}