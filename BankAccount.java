//separating access to reading from storage from player obj
//Will reuse as Dealer account
import java.lang.String;

import java.io.*;

public class BankAccount {
	private String accountOwner;
	private double accountTotal = 100.00;
	private FileWriter bankFile;
	private InputReader inputReader;

public BankAccount(String owner, InputReader inputReader) {
	this.accountOwner = owner;
	this.inputReader = inputReader;
	this.bankFile = new FileWriter(generatePlayerFileName(inputReader, true);
}

public BankAccount(String owner, double total) {
	this.accountOwner = owner;
	this.accountTotal = total;
}

//feelin like this is redundant
public boolean validateFile(File file) { //should I use file obj ref or string of file name?
	boolean boolVal = false;

	//FIXME: need to check data folder for the filetype and return true.
	if(file.exists()) {
		boolVal = true;
	}
	else {
		System.out.println("Error, file does not exist");
	}

	return boolVal;
}

public double getAccountTotal() {
	return this.accountTotal;
}

public String getAccountOwner() {
	return this.accountOwner;
}

public String generateAccountFileName() {
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


public void getFileValues(File bankFile) {

}
}