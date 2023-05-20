import java.io.*;

public class Game {
	//Only declaration, need inputReader obj declared in main()
	private InputReader inputReader;
	private GameInstance gameInstance = null;
	
	//constructors
	public Game(InputReader inputReader) throws IOException { //needs scanner, perhaps object game type?
		this.inputReader = inputReader;
		handleMenuInput();
	} 

	public void handleMenuInput() {
		String menuInput;
		int menuInputVal = -1;

		while(true) {
			generateMenu();

			//used to prevent inputReader from reading from keyboard here and inside of gameInstance
			if(gameInstance == null) {
				menuInput = inputReader.nextLine();

				try {
					menuInputVal = Integer.parseInt(menuInput);
				}
				catch (Exception e) {
					System.out.println("Error, cannot parse int from menuInput String variable\n");
				}
			}
			
			if(menuInputVal == 1) { //begins game loop
				try {
					GameInstance gameInstance = new GameInstance(inputReader);	
				}
				catch (Exception e) {
					System.out.println("error");
				}
			
			}
			else if(menuInputVal == 2) {
				break; 
			}
			else {
					System.out.println("Error, value is either WIP or invalid.");
			}
		}	
	}
		

	public void generateMenu() {
		System.out.println("\nSelect a Game");
		System.out.println("1: Blackjack-Dice\n");
		System.out.println("2: Quit\n");
		System.out.print("Select: ");
		/*
		System.out.println("Future Games");
		System.out.println("2: Texas Hold'em");
		System.out.println("3: AI Go-fish");		
		*/
	}
}