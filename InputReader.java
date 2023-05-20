import java.util.Scanner;

/*
Class made for encapsulating the scanner for System.in Input, allowing for multiple
Components to access the same Scanner obj.
*/
public class InputReader {
	private Scanner scnr;

	public InputReader() {
		this.scnr = new Scanner(System.in);
	}



	public String nextLine() {
		return scnr.nextLine();
	}

	public Double nextDouble() {
		return scnr.nextDouble();
	}

	public int nextInt() {
		return scnr.nextInt();
	}

	public void clearInputReader() {
		//advances scanner to nextline, no data gathered.
		scnr.nextLine();
	}

	public void close() {
		scnr.close();
	}
}