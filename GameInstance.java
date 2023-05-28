import java.io.*;


public class GameInstance {

	//Controls how many sides are used in the dice
	final int TOTALSIDES = 6;

	//Controls the bust point for blackjack
	final int MAXTOTAL = 21;
	
	//Objects control writing for gamelogs
	FileWriter gameLogHandler = new FileWriter("output.txt", true);
	PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);
	
	InputReader inputReader;

	//variable for menu control
	private int menuInputVal;

	//game variables
	private int playerTotal;
	private int dealerTotal;

	//betting mechanic variables
	private double MoneyPot;
	private double dealerMoneyTotal;
	private double playerBet;
	private BankAccount playerAccount = new BankAccount("Cheddar", "bankone.txt");
	
	//Dice objects are immediately declared/initialized with TOTALSIDES
	private Dice playerDiceOne = new Dice(TOTALSIDES);
	private Dice playerDiceTwo = new Dice(TOTALSIDES);
	private Dice dealerDiceOne = new Dice(TOTALSIDES);
	private Dice dealerDiceTwo = new Dice(TOTALSIDES);


	/*
	when opening blackjack as the game...

	1. welcome player
	2. show playersaves (shown as credit cards) as a menu option
	3. utilize that 'credit card' for the betting phase and winphases

	methods needed

	greeting(): void
	profileDirReader(String: path): 
	*/

	public GameInstance(InputReader inputReader) throws IOException {
		this.inputReader =	inputReader; 

		greetPlayer();
		handleBettingPhase();
		handleGamePhase();
	}

	public void greetPlayer() {
		System.out.println("Welcome to BlackJack! This is a version that is played with Dice.\n");
	}

	public void setPlayerBet() {

		System.out.print("Enter a value(double) to bet this round: ");
		this.playerBet = inputReader.nextDouble();
	}

	public void handleBettingPhase() throws IOException {
		System.out.println("Bank Total: $" + playerAccount.getAccountBalance());

		setPlayerBet();

		if(playerBet <= playerAccount.getAccountBalance()) {
			playerAccount.updateAccountBalance((playerBet * -1.0));
			setMoneyPot((playerBet * 2.00));
		}
	}

	public void handleGamePhase() throws IOException {
		//new initial dice rolls to get going
		updatePlayerTotal();
		updateDealerTotal();
		
		//New Game logs
		gameLogWriter.println("New Match");
		gameLogWriter.println("Initial player roll: " + playerTotal);

		//main loop
		while(true) {
			handleMenuInput();
			break;
		}

		//closing log handlers after gameloop ends
		gameLogHandler.close();
		gameLogWriter.close();
	}

	public void setMoneyPot(double money) {
		this.MoneyPot = money;
	}

	public double getMoneyPot() {
		return this.MoneyPot;
	}

	public void updateMoneyPot(double money) {
		setMoneyPot(getMoneyPot() + money);
	}

	public int rollDice(Dice one, Dice two) {
		return (one.getValue() + two.getValue());
	}

	public void updatePlayerTotal() {
		setPlayerTotal(getPlayerTotal() + rollDice(playerDiceOne, playerDiceTwo));
	}

	public void updateDealerTotal() {
		setDealerTotal((getDealerTotal() + rollDice(dealerDiceOne, dealerDiceTwo)));
	}

	public int getPlayerTotal() {
		return playerTotal;
	}

	public int getDealerTotal() {
		return dealerTotal;
	}

	public void setDealerTotal(int total) {
		this.dealerTotal = total;
	}

	public void setPlayerTotal(int total) {
		this.playerTotal = total;
	}

/*
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
*/

	public void handleGameOutcomeLog(String msg, boolean gameWon) {
		//GAME LOGS
		if(gameWon) {
			//WIN
			System.out.println("Game result: " + msg);
			System.out.println("Moneypot Reward: $" + getMoneyPot());
			gameLogWriter.println("Game result: " + msg);
			System.out.println("Moneypot Reward: $" + getMoneyPot());
		}
		else {
			//LOSS
			System.out.println("Game result: " + msg);
			System.out.println("Lost MoneyPot: $" + getMoneyPot());
			gameLogWriter.println("Game result: " + msg);
			System.out.println("Lost MoneyPot: $" + getMoneyPot());
		}
	}


	public void handleGameOutcome(Boolean bool) throws IOException {
		Boolean gameWon = bool;
		if(gameWon) {
			if(getDealerTotal() > MAXTOTAL) {
				handleGameOutcomeLog("DEALER BUST", gameWon);
				playerAccount.updateAccountBalance(MoneyPot);

			}
			else {
				handleGameOutcomeLog("WIN", gameWon);
				playerAccount.updateAccountBalance(MoneyPot);
			}
		}
		else if(!gameWon) {
			if(getPlayerTotal() > MAXTOTAL) {
				handleGameOutcomeLog("BUST", gameWon);
			}
			else {
				handleGameOutcomeLog("LOSS", gameWon);
			}
		}
		else {
			handleGameOutcomeLog("DRAW", gameWon);
			playerAccount.updateAccountBalance((MoneyPot * 0.5));
		}
	}


	public void handleMenuInput() throws IOException {
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
			updatePlayerTotal();
							
			//GAME LOGS
			System.out.println("\nPlayer rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".");
			gameLogWriter.print("Player rolls dice: " + playerDiceOne.getValue() + " and " + playerDiceTwo.getValue() + ".\n");
				
			//if option 1 goes over MAXTOTAL, generate logs & break
			if(playerTotal > MAXTOTAL) {

				handleGameOutcome(false);
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
				//DRAW
				handleGameOutcome(false);
				break;
				}
			else if(playerTotal > dealerTotal) {
				//WIN
				handleGameOutcome(true);
				break;
			}
			else {
				//GAME LOGS
				handleGameOutcome(false);
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
				updateDealerTotal();

				//GAME LOGS
				System.out.println("\nDealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");
				gameLogWriter.println("Dealer rolls dice: " + dealerDiceOne.getValue() + " and " + dealerDiceTwo.getValue() + ".");					
				System.out.println("\nDealer Total: " + getDealerTotal());
				gameLogWriter.println("Dealer Total: " + getDealerTotal());
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
	}
}