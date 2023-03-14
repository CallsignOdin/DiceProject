//possibly to use for holding bank account total : Double
//playername : String
//Hand Wins: Int
//Hand Losses: Int
//Total Losses: Int
//Lost to Dealer: Int
//Losttobusting: Int
//NetworthChange: Double
//
public class Player {
	//need input validation because name will become filename, must prevent invalid file names.
	private String name;
	private int handWins;
	private int handLosses;
	private BankAccount bank;
	private File storage;
	FileWriter gameLogHandler = new FileWriter(generatePlayerFileName(), true);
	PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);

	//constructor
	Player(String name) {
		this.name = name;
		if(this.storage != null) {//ERROR: Need to check if file exists in location specified and gen if not
			createDataFile()
		}
		else {

		}
		
	}

	public String getPlayerName() {
		return this.name;
	}

	public String generatePlayerFileName() {
		return (getPlayerName() + ".txt");
	}


	//handles both wins/losses
	public void updateBank(double total) {
		this.bank.accountTotal += total;
	}

	//creates new file to store data
	public void createDataFile() {
		this.storage = new File((generatePlayerFileName() + ".txt"));
	}

}