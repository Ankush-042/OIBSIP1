import java.util.Scanner;

public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM System");

        Account currentAccount = authenticateUser();

        if (currentAccount != null) {
            int choice;
            do {
                displayMenu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        transactionHistory(currentAccount);
                        break;
                    case 2:
                        withdraw(currentAccount);
                        break;
                    case 3:
                        deposit(currentAccount);
                        break;
                    case 4:
                        transfer(currentAccount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }

    private static Account authenticateUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        Account account = bank.authenticate(userId, pin);
        return account;
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void transactionHistory(Account account) {
        System.out.println("\nTransaction History:");
        account.getTransactionHistory().forEach(System.out::println);
    }

    private static void withdraw(Account account) {
        System.out.print("\nEnter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance() + " rs");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void deposit(Account account) {
        System.out.print("\nEnter deposit amount: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance() + " rs");
    }

    private static void transfer(Account fromAccount) {
        System.out.print("\nEnter recipient user ID: ");
        String recipientId = scanner.next();
        Account recipientAccount = bank.getAccount(recipientId);

        if (recipientAccount == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();

        if (fromAccount.transfer(amount, recipientAccount)) {
            System.out.println("Transfer successful. New balance: " + fromAccount.getBalance() + " rs");
        } else {
            System.out.println("Transfer failed. Insufficient funds.");
        }
    }
}
