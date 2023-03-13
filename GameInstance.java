import java.io.*;
import java.util.Scanner;

public class GameInstance {
	final int TOTALSIDES = 6;
	final int MAXTOTAL = 21;
	FileWriter gameLogHandler = new FileWriter("output.txt", true);
	PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);
	private Scanner menuInput = new Scanner(System.in);
	private int menuInputVal;
	private int playerTotal;
	private int dealerTotal;
	private Dice playerDiceOne = new Dice(TOTALSIDES);
	private Dice playerDiceTwo = new Dice(TOTALSIDES);
	private Dice dealerDiceOne = new Dice(TOTALSIDES);
	private Dice dealerDiceTwo = new Dice(TOTALSIDES);
	private boolean running = true;

	public GameInstance() throws IOException {
		

		//calculating value for evaluation
		playerTotal = playerDiceOne.getValue() + playerDiceTwo.getValue();
		dealerTotal = dealerDiceOne.getValue() + dealerDiceTwo.getValue();

		//New Game logs
		gameLogWriter.println("New Match");
		gameLogWriter.println("Initial player roll: " + playerTotal);

		//initial game loop
		while(running) {
			handleMenuInput();	
		}
		

		gameLogHandler.close();
		gameLogWriter.close();
	}

	public void handleMenuInput() {
		while(playerDiceOne.getValue() + playerDiceTwo.getValue()  <= MAXTOTAL ) {
		menuInputVal = getMenuValue();

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
					
				running = false;
				break;
			}			
		}
		else if(menuInputVal == 2) {
			//display & log player/dealer values & determine win
				
			//GAME LOGS
			System.out.println("\nDealer Total Exposed: " + dealerTotal);
			gameLogWriter.println("Dealer Total Exposed: " + dealerTotal);
				

			//evaluating dealer rolls or call, only rolling when rolling will NOT bust the game
			updateDealerHand();
				
			//GAME LOGS
			System.out.println("\nPlayer final Total: " + playerTotal);
			gameLogWriter.println("Player final Total: " + playerTotal);
			System.out.println("Dealer final Total: " + dealerTotal);
			gameLogWriter.println("Dealer final Total: " + dealerTotal);
					

			if(playerTotal == dealerTotal) {
				//GAME LOGS
				System.out.println("Game result: DRAW");

						
				gameLogWriter.println("Game result: DRAW");

					running	= false;
					break;
				}
				else if(playerTotal > dealerTotal) {
					//GAME LOGS
					System.out.println("Game result: WIN");

						
					gameLogWriter.println("Game result: WIN");

					running = false;
					break;
				}
				else {
					//GAME LOGS
					System.out.println("Game result: LOSS");						
					gameLogWriter.println("Game result: LOSS");

					running = false;
					break;
				}
		}

		else if(menuInputVal == 3) {
			//GAME LOGS
			System.out.println("Game Closing");

				
			gameLogWriter.println("Game Closed");

			running = false;
			break;
		}
		else {
			System.out.println("Error, please retry");
		}

	}}

	public void updateDealerHand() {
		//evaluating dealer rolls or call, only rolling when rolling will NOT bust the game
		if(dealerTotal <= MAXTOTAL - 12) {
			while(dealerTotal <= MAXTOTAL - 12) {
			dealerDiceOne.roll();
			dealerDiceTwo.roll();
			dealerTotal += (dealerDiceOne.getValue() + dealerDiceTwo.getValue());

			//GAME LOGS
			System.out.println("\nDealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");
			gameLogWriter.println("Dealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");					
			System.out.println("\nDealer Total: " + dealerTotal);					gameLogWriter.println("Dealer Total: " + dealerTotal);
			}
		}
	}


	public void displayMenu() {
		System.out.println("Player Total: " + playerTotal + "\n");
		System.out.println("1: Roll Dice");
		System.out.println("2: Call Dealer");
		System.out.println("3: End Game\n");
		System.out.print("Please enter an option: ");
	}

	public int getMenuValue() {
		displayMenu();
		return menuInput.nextInt();
	}}