import java.io.*;

public class Game {
	//Only declaration, need inputReader obj declared in main()
	
	private GameInstance gameInstance = null;
	private InputReader inputReader;

	//constructors
	public Game(InputReader inputReader) throws IOException { //needs scanner, perhaps object game type?
		this.inputReader = inputReader;
		handleMenuInput();
	} 

	public void handleMenuInput() throws IOException {
		String menuInput;
		int menuInputVal = -1;

		while(true) {
			generateMenu();

		
			
			boolean valid = false;
			while(!valid) {
				menuInput = inputReader.nextLine();
				menuInputVal = Integer.parseInt(menuInput);

				if(menuInputVal == 1 || menuInputVal == 2) {
					valid = true;
				} 
				else {
					System.out.println("Error, incorrect input.");
				}
			}

			if(menuInputVal == 1) { //begins game loop			
				setGameInstance();	
			}
				
			else if(menuInputVal == 2) {
				break; 
			}
			else {
				System.out.println("Error, value is either WIP or invalid.");
			}
			

		}	
	}

	public void setGameInstance(/*future game type go here*/) throws IOException {
		this.gameInstance = new GameInstance(inputReader);
	}

	public void clearGameInstance() {
		this.gameInstance = null;
	}

	public void generateMenu() {
		System.out.println("\nSelect a Game");
		System.out.println("1: Blackjack-Dice");
		System.out.println("2: Quit\n");
		System.out.print("Select: ");
	}
}