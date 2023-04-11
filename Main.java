/**
*Description: This program Drives a blackjack game
*Date: 02/27/2023
*@version 0.0.0
*/ 
import java.io.*;
import java.util.Scanner;

public class Main {
	static public Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		File welcomeMsgFile = new File("input.txt");
		Scanner inputFile = new Scanner(welcomeMsgFile);

		String welcomeMsg = inputFile.nextLine();
		System.out.println(welcomeMsg);
		System.out.println();

		inputFile.close();

		//starts an instance of Dice blackjack
		Game game = new Game(scnr);
	}
}