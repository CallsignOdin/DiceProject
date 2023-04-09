//separating access to reading from storage from player obj
//Will reuse as Dealer account
import java.lang.String;
import java.util.Scanner;

public class BankAccount {
	private String accountOwner;
	private double accountTotal = 100.00;
	private FileWriter bankFile;
}

public BankAccount(String owner) {
	this.accountOwner = owner;
	this.bankFile = new FileWriter("bankone.txt", true);
}

public BankAccount(String owner, double total) {
	this.accountOwner = owner;
	this.accountTotal = total;
}

public double getAccountTotal() {
	return this.accountTotal;
}

public String getAccountOwner() {
	return this.accountOwner;
}

public String generateAccountFileName(Scanner scnr) {
	String inputVal;
	boolean nameVerified = false;
	int i;

	while(!nameVerified) {
		if(this.accountOwner.trim().isEmpty()) {
			System.out.println("Enter a name to generate account file:");
			inputVal = scnr.nextLine();
		
			//input validation
			for(i = 0; i < inputVal.length(); i++) {
				if(!inputVal.charAt(i).isLetter()) {
					System.out.println("Error, invalid character: " + inputVal.charAt(i)); //Add arrayList collection to print out invalid characters 
					break;				
				}
				else if((inputVal.charAt(i).isLetter()) && (i == inputVal.length() - 1)) { //valid conditional but bad
					nameVerified = true;
				}
			}
		}		
	}

	inputVal = inputVal.concat(".txt");

	return inputVal;
}


