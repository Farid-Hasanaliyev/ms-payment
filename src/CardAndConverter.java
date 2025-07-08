import java.util.Scanner;

public class CardAndConverter {

    // Conversion rate constant
    private static final double USD_TO_AZN_RATE = 1.7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Part 1: Credit Card Brand Detection
        System.out.print("Enter your card number (16 digits): ");
        String cardNumber = scanner.nextLine().trim();

        if (isValidCardNumber(cardNumber)) {
            char firstDigit = cardNumber.charAt(0);
            String brand;
            if (firstDigit == '4') {
                brand = "Visa";
            } else if (firstDigit == '5' || firstDigit == '6') {
                brand = "Mastercard";
            } else {
                brand = "Unknown";
            }
            System.out.println("Card brand: " + brand);
        } else {
            System.out.println("Invalid card number. Must be exactly 16 digits.");
            scanner.close();
            return;
        }

        // Part 2: USD → AZN Converter
        System.out.print("\nHow many payments will you enter? ");
        int totalPayments;
        try {
            totalPayments = Integer.parseInt(scanner.nextLine().trim());
            if (totalPayments <= 0) {
                System.out.println("Number of payments must be positive.");
                scanner.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            scanner.close();
            return;
        }

        int convertedCount = 0;
        int skippedCount = 0;
        double sumUsd = 0.0;
        double sumAzn = 0.0;

        for (int i = 1; i <= totalPayments; i++) {
            System.out.print("Enter amount in USD for payment #" + i + ": ");
            double amountUsd;
            try {
                amountUsd = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  → Invalid input, skipping.");
                skippedCount++;
                continue;
            }

            if (amountUsd < 0) {
                System.out.println("  → Negative value entered, skipping.");
                skippedCount++;
            } else {
                double amountAzn = amountUsd * USD_TO_AZN_RATE;
                sumUsd += amountUsd;
                sumAzn += amountAzn;
                convertedCount++;
                System.out.printf("  → %.2f USD = %.2f AZN%n", amountUsd, amountAzn);
            }
        }

        // Summary
        System.out.println("\n--- Conversion Summary ---");
        System.out.println("Payments converted: " + convertedCount);
        System.out.println("Payments skipped:   " + skippedCount);
        System.out.printf("Total USD: %.2f%n", sumUsd);
        System.out.printf("Total AZN: %.2f%n", sumAzn);

        scanner.close();
    }

    /**
     * Validates that the card number string is exactly 16 digits.
     */
    private static boolean isValidCardNumber(String s) {
        return s.matches("\\d{16}");
    }
}
