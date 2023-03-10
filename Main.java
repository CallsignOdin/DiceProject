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
	public static void main(String[] args) throws IOException {
		final int TOTALSIDES = 6;
		final int MAXTOTAL = 21;
		File welcomeMsgFile = new File("input.txt");
		FileWriter gameLogHandler = new FileWriter("output.txt", true);
		PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);
		Scanner inputFile = new Scanner(welcomeMsgFile);
		Scanner menuInput = new Scanner(System.in);
		int menuInputVal;
		int playerTotal;
		int dealerTotal;
		String welcomeMsg = inputFile.nextLine();
		

		Dice playerDiceOne = new Dice(TOTALSIDES);
		Dice playerDiceTwo = new Dice(TOTALSIDES);
		Dice dealerDiceOne = new Dice(TOTALSIDES);
		Dice dealerDiceTwo = new Dice(TOTALSIDES);

		playerTotal = playerDiceOne.getValue() + playerDiceTwo.getValue();
		dealerTotal = dealerDiceOne.getValue() + dealerDiceTwo.getValue();



		System.out.println(welcomeMsg);
		System.out.println();
		inputFile.close();


		gameLogWriter.println("New Match");
		gameLogWriter.println("Initial player roll: " + playerTotal);

		//initial game loop
		while(playerDiceOne.getValue() + playerDiceTwo.getValue()  <= MAXTOTAL ) {
			System.out.println("Player Total: " + playerTotal + "\n");
			System.out.println("1: Roll Dice");
			System.out.println("2: Call Dealer");
			System.out.println("3: End Game\n");
			System.out.print("Please enter an option: ");

			menuInputVal = menuInput.nextInt();

			if(menuInputVal == 1) {
				playerDiceOne.roll();
				playerDiceTwo.roll();
				playerTotal += (playerDiceOne.getValue() + playerDiceTwo.getValue());
				
				//GAME LOGS
				System.out.println("\nPlayer rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".");
				gameLogWriter.print("Player rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".\n");
				

				if(playerTotal > MAXTOTAL) {

					//GAME LOGS
					System.out.println("Game result: BUST");
					gameLogWriter.println("Game result: BUST");
					
					break;
				}
			}
			else if(menuInputVal == 2) {
				//display & log player/dealer values & determine win
				
				//GAME LOGS
				System.out.println("\nDealer Total Exposed: " + dealerTotal);
				gameLogWriter.println("Dealer Total Exposed: " + dealerTotal);
				

				//evaluating dealer rolls or call, only rolling when rolling will NOT bust the game
				if(dealerTotal <= MAXTOTAL - 12) {
					while(dealerTotal <= MAXTOTAL - 12) {
					dealerDiceOne.roll();
					dealerDiceTwo.roll();
					dealerTotal += (dealerDiceOne.getValue() + dealerDiceTwo.getValue());

					//GAME LOGS
					System.out.println("\nDealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");
					gameLogWriter.println("Dealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");					
					System.out.println("\nDealer Total: " + dealerTotal);
					gameLogWriter.println("Dealer Total: " + dealerTotal);
					}
				}
				
					//GAME LOGS
					System.out.println("\nPlayer final Total: " + playerTotal);
					gameLogWriter.println("Player final Total: " + playerTotal);
					System.out.println("Dealer final Total: " + dealerTotal);
					gameLogWriter.println("Dealer final Total: " + dealerTotal);
					

					if(playerTotal == dealerTotal) {
						//GAME LOGS
						System.out.println("Game result: DRAW");

						
						gameLogWriter.println("Game result: DRAW");

						break;
					}
					else if(playerTotal > dealerTotal) {
						//GAME LOGS
						System.out.println("Game result: WIN");

						
						gameLogWriter.println("Game result: WIN");
						break;
					}
					else {
						//GAME LOGS
						System.out.println("Game result: LOSS");

						
						gameLogWriter.println("Game result: LOSS");
						break;
					}
			}

			else if(menuInputVal == 3) {
				//GAME LOGS
				System.out.println("Game Closing");

				
				gameLogWriter.println("Game Closed");
				break;
			}
			else {
				System.out.println("Error, please retry");
			}

			
			//gameLogHandler.close();
		}
		gameLogWriter.close();
	}
}
