/**
*Description: This program Drives a blackjack game
*Date: 02/27/2023
*@version 0.0.0
*/ 
import java.io.*;
import java.util.Scanner;

public class Main {
/**
* @param String as args
* @return Termination code as int, 0 for normal, anything else is error condition
* @throws Nothing is implemented
*/
	public static void main(String[] args) throws IOException{
		File welcomeMsgFile = new File("input.txt");
		Scanner inputFile = new Scanner(welcomeMsgFile);
		String welcomeMsg = inputFile.nextLine();
		System.out.println(welcomeMsg);
		System.out.println();
		inputFile.close();
		GameInstance game = new GameInstance();
	}
}