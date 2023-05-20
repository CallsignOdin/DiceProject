import java.io.*;


public class GameInstance {

	//Controls how many sides are used in the dice
	final int TOTALSIDES = 6;

	//Controls the bust point for blackjack
	final int MAXTOTAL = 21;
	
	//Objects control writing for gamelogs
	FileWriter gameLogHandler = new FileWriter("output.txt", true);
	PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);
	
	 //encapsulated Scanner object
	private InputReader inputReader;

	//variable for menu control
	private int menuInputVal;

	//game variables
	private int playerTotal;
	private int dealerTotal;
	
	//Dice objects are immediately declared/initialized with TOTALSIDES
	private Dice playerDiceOne = new Dice(TOTALSIDES);
	private Dice playerDiceTwo = new Dice(TOTALSIDES);
	private Dice dealerDiceOne = new Dice(TOTALSIDES);
	private Dice dealerDiceTwo = new Dice(TOTALSIDES);

	public GameInstance(InputReader inputReader) throws IOException {
		//reutilizing the same InputReader object declared in main()
		this.inputReader = inputReader;

		//Initial roll for evaluation
		playerTotal = playerDiceOne.getValue() + playerDiceTwo.getValue();
		dealerTotal = dealerDiceOne.getValue() + dealerDiceTwo.getValue();

		//New Game logs
		gameLogWriter.println("New Match");
		gameLogWriter.println("Initial player roll: " + playerTotal);

		//initial game loop
		while(true) {
			handleMenuInput();
			break;
		}
		
		//closing log handlers after gameloop ends
		gameLogHandler.close();
		gameLogWriter.close();
	}


	public void handleMenuInput() {
		//loop checks if player busted while in last iteration and allow player
		while(playerDiceOne.getValue() + playerDiceTwo.getValue() <= MAXTOTAL ) {

		//used to evaluate menu options
		menuInputVal = getMenuValue(inputReader);

		//option 1: Roll Dice
		if(menuInputVal == 1) {
			//Updating dice to new values
			playerDiceOne.roll();
			playerDiceTwo.roll();

			

			//using new dice to update playerTotal
			playerTotal += (playerDiceOne.getValue() + playerDiceTwo.getValue());
			
			
				
			//GAME LOGS
			System.out.println("\nPlayer rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".");
			gameLogWriter.print("Player rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".\n");
				

			//if option 1 goes over MAXTOTAL, generate logs & break
			if(playerTotal > MAXTOTAL) {

				//GAME LOGS
				System.out.println("Game result: BUST");
				gameLogWriter.println("Game result: BUST");
					
				break;
			}			
		}
		//option 2: Call Dealer
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
					

			//Determines win
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

	}}

	public void updateDealerHand() {
		//evaluating dealer rolls or call, only rolling when rolling will NOT bust the game
		if(dealerTotal <= MAXTOTAL && dealerTotal <= playerTotal) {
			while(dealerTotal <= MAXTOTAL && dealerTotal <= playerTotal) {
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
		//never executes
		else {
			//GAME LOGS
			System.out.println("Dealer does not roll dice this round.");
			gameLogWriter.println("Dealer does not roll dice this round.");
			System.out.println("\nDealer Total: " + dealerTotal);
			gameLogWriter.println("Dealer Total: " + dealerTotal);
		}
	}


	//displays menu
	public void displayMenu() {
		System.out.println("Player Total: " + playerTotal + "\n");
		System.out.println("1: Roll Dice");
		System.out.println("2: Call Dealer");
		System.out.println("3: End Game\n");
		System.out.print("Please enter an option: ");
	}

	public int getMenuValue(InputReader inputReader) {
		int returnVal;
		displayMenu();
		returnVal = inputReader.nextInt();
		inputReader.clearInputReader();
		return returnVal;
	}}