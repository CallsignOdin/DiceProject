import java.util.Scanner;
import java.io.*;

public class Game {
	public Scanner scnr = new Scanner(System.in);
	
	//constructors
	public Game(Scanner scnr) throws IOException { //needs scanner, perhaps object game type?
		handleMenuInput();

	} 

	public void handleMenuInput() {
		String menuInput;
		int menuInputVal = -1;
		generateMenu();

		menuInput = scnr.nextLine();

		try {
			menuInputVal = Integer.parseInt(menuInput);
		}
		catch (Exception e) {
			System.out.println("Error, cannot parse int from menuInput String variable\n");
			
		}


		if(menuInputVal == 1) { //begins game loop
			try {
				GameInstance blackJack = new GameInstance(scnr);	
			}
			catch (Exception e) {
				System.out.println("error");
			}
			
		}
		else {
			System.out.println("Error, value is either WIP or invalid.");
		}
	}

	public void generateMenu() {
		System.out.println("Select a Game:");
		System.out.println("1: BlackDick-Dice\n");

		System.out.println("Future Games");
		System.out.println("2: Texas Hold'em");
		System.out.println("3: AI Go-fish");		
	}
}