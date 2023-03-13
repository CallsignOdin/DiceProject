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
	private String name;
	private int handWins;
	private int handLosses;
	private BankAccount bank;
	private File storage;

	Player(String name) {
		this.name = name;
	}


	//handles both wins/losses
	public void updateBank(double total) {
		this.bank.accountTotal += total;
	}

	public void createDataFile()

}