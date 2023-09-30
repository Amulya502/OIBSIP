package sample;

import java.util.Scanner;

class Bankaccount {
    String name;
    String userName;
    String password;
    String accountNo;
    int prevTransaction;
    int balance = 500000;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Account Registration ===");
        System.out.print("Enter your full name: ");
        this.name = input.nextLine();
        System.out.print("Create a username: ");
        this.userName = input.nextLine();
        System.out.print("Set a password: ");
        this.password = input.nextLine();
        System.out.print("Enter your account number: ");
        this.accountNo = input.nextLine();
        System.out.println("Account successfully created! Please log in to proceed.");
    }

    public void checkBalance() {
        System.out.println("\n=== Check Balance ===");
        System.out.println("Current balance: Rs. " + balance);
    }

    public void depositMoney() {
        System.out.println("\n=== Deposit Money ===");
        System.out.print("Enter the amount to deposit: Rs. ");
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        try {
            if (amount <= 100000) {
                transactions++;
                balance += amount;
                prevTransaction = amount;
                System.out.println("Deposit successful: Rs. " + amount);
                String str = "Rs. " + amount + " deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Apologies, the maximum deposit limit is Rs. 100,000.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
        }
    }

    public void withdrawMoney() {
        System.out.println("\n=== Withdraw Money ===");
        System.out.print("Enter the amount to withdraw: Rs. ");
        Scanner input = new Scanner(System.in);
        int amount = input.nextInt();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                prevTransaction -= amount;
                System.out.println("Withdrawal successful: Rs. " + amount);
                String str = "Rs. " + amount + " withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Insufficient balance. Withdrawal not possible.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
        }
    }

    public void transferMoney() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Transfer Money ===");
        System.out.print("Enter the receiver's name: ");
        String receiver = input.nextLine();
        System.out.print("Enter the amount to transfer: Rs. ");
        int amount = input.nextInt();
        try {
            if (balance >= amount) {
                if (amount <= 100000) {
                    transactions++;
                    balance -= amount;
                    System.out.println(amount + " Rs. successfully transferred to " + receiver);
                    String str = amount + " Rs. transferred to " + receiver + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("Sorry, the maximum transfer limit is Rs. 100,000.");
                }
            } else {
                System.out.println("Transfer failed due to insufficient balance.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid amount.");
        }
    }

    public void getLastTransaction() {
        if (prevTransaction > 0) {
            System.out.println("Last transaction: Deposited Rs. " + prevTransaction);
        } else if (prevTransaction < 0) {
            System.out.println("Last transaction: Withdrew Rs. " + Math.abs(prevTransaction));
        } else {
            System.out.println("No previous transactions.");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("\n=== Transaction History ===");
        if (transactions == 0) {
            System.out.println("No transactions to display.");
        } else {
            System.out.println(transactionHistory);
        }
    }

    public boolean logIn() {
        boolean isLoggedIn = false;
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Log In ===");
        while (!isLoggedIn) {
            System.out.print("Enter your username: ");
            String enteredUsername = input.nextLine();
            if (enteredUsername.equals(userName)) {
                while (!isLoggedIn) {
                    System.out.print("Enter your password: ");
                    String enteredPassword = input.nextLine();
                    if (enteredPassword.equals(password)) {
                        System.out.println("Login successful! Welcome, " + name + "!");
                        isLoggedIn = true;
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Please check your username.");
            }
        }
        return isLoggedIn;
    }
}

public class ATMInterface {
    public static int getUserChoice(int limit) {
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                validChoice = true;
                if (validChoice && (choice > limit || choice < 1)) {
                    System.out.println("Choose a number between 1 and " + limit + ".");
                    validChoice = false;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                validChoice = false;
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("==== WELCOME TO OUR ATM SERVICE ====");
        System.out.println("=====================================");

        System.out.println("\n1. Create Account\n2. Exit");
        System.out.print("Enter your choice: ");
        int initialChoice = getUserChoice(2);

        if (initialChoice == 1) {
            Bankaccount account = new Bankaccount();
            account.register();
            while (true) {
                System.out.println("\n1. Log In\n2. Exit");
                System.out.print("Enter your choice: ");
                int loginChoice = getUserChoice(2);

                if (loginChoice == 1) {
                    if (account.logIn()) {
                        System.out.println("\nWelcome, " + account.name + "!");
                        boolean finished = false;
                        while (!finished) {
                            System.out.println("\n1. Check Balance\n2. Deposit Money\n3. Withdraw Money\n4. Transfer Money\n5. View Last Transaction\n6. View Transaction History\n7. Log Out");
                            System.out.print("Enter your choice: ");
                            int actionChoice = getUserChoice(7);

                            switch (actionChoice) {
                                case 1:
                                    account.checkBalance();
                                    break;
                                case 2:
                                    account.depositMoney();
                                    break;
                                case 3:
                                    account.withdrawMoney();
                                    break;
                                case 4:
                                    account.transferMoney();
                                    break;
                                case 5:
                                    account.getLastTransaction();
                                    break;
                                case 6:
                                    account.viewTransactionHistory();
                                    break;
                                case 7:
                                    finished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
