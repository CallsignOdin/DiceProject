//separating access to reading from storage from player obj
//Will reuse as Dealer account
public class BankAccount {
	private String accountOwner;
	private double accountTotal = 100.00;
	private File storage;
}

public BankAccount(String owner) {
	this.accountOwner = owner;
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


