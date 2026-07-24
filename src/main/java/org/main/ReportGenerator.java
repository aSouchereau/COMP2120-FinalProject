package org.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void generateUserReport(String name, List<Account> accounts, String filename) throws IOException {

        try (FileWriter writer = new FileWriter(filename)) {

            writer.write("=== BANK REPORT FOR USER: " + name + " ===\n\n");

            if (accounts.isEmpty()) {
                writer.write("No accounts found for this user.\n");
                return;
            }

            for (Account acc : accounts) {
                writer.write("Account Number: " + acc.getAccountNumber() + "\n");
                writer.write("Account Type: " + acc.getAccountType() + "\n");
                writer.write("Balance: $" + acc.getBalance() + "\n");
                writer.write("Transactions:\n");

                for (Transaction t : acc.getTransactions()) {
                    writer.write("  - " + t.toString() + "\n");
                }

                writer.write("\n---------------------------\n\n");
            }
        }
    }
}
