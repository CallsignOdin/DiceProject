import java.util.Scanner;
import java.io.*;
import java.util.*;

public class BankFileReader {
    private List<String> lines;
    private File bankFile;

    public BankFileReader(File filename) throws IOException {
        this.bankFile = filename;
        this.lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.bankFile)) {
            //Will work with more data on lines
            while (scanner.hasNextLine()) {
                this.lines.add(scanner.nextLine());
            }
        }
    }

    public String readLine(int lineNumber) {
        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            return lines.get(lineNumber - 1);
        } else {
            return null;
        }
    }

    public void updateBankFileBalance(double amountToAdd) throws IOException  {
        double newBankValue = Double.parseDouble(readLine(2));
        newBankValue += amountToAdd;
        lines.set(1, (Double.toString(newBankValue)));

        // After modification, write the changes to the file.
        try (PrintWriter writer = new PrintWriter(new FileWriter(bankFile))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }

    public void setBankFileOwner(String owner) throws IOException {
        lines.set(0, owner);
        
        // After modification, write the changes to the file.
        try (PrintWriter writer = new PrintWriter(new FileWriter(bankFile))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }

    public void updateLine(int lineNumber, double newContent) throws IOException {
        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            lines.set(lineNumber - 1, (Double.toString(newContent)));
        }
        // After modification, write the changes to the file.
        try (PrintWriter writer = new PrintWriter(new FileWriter(bankFile))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
}
