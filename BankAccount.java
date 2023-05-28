//separating access to reading from storage from player obj
//Will reuse as Dealer account
import java.lang.String;
import java.util.Scanner;
import java.nio.file.*;
import java.io.*;

public class BankAccount {
	private String accountOwner;
	private double accountBalance = 100.00;
	private File bankFile;
	private BankFileReader bankReader;


		//Objects control writing for gamelogs
	//private FileWriter gameLogHandler = new FileWriter("output.txt", true);
	//private PrintWriter gameLogWriter = new PrintWriter(gameLogHandler);


	public BankAccount(String owner, String mainFile) throws IOException {
		this.accountOwner = owner;
		this.bankFile = new File(mainFile);
		this.bankReader = new BankFileReader(bankFile);

	}

	public String createFileName(String owner) {
		String returnValue = owner.concat(".txt");
		
		return returnValue;
	}

	public String getFileName() {
		return this.bankFile.getName();
	}

	public String getAccountOwner() {
		return bankReader.readLine(1);
	}

	public double getAccountBalance() {
		return (Double.parseDouble(bankReader.readLine(2)));
	}


	public void updateAccountBalance(double input) throws IOException {
		bankReader.updateBankFileBalance(input);
	}
}