import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("==== Expense Tracker ====");

        do {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Show Total by Category");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter note: ");
                    String note = sc.nextLine();
                    LocalDate date = LocalDate.now();

                    manager.addExpense(amount, category, note, date);
                    System.out.println("Expense added successfully.");
                }
                case 2 -> manager.viewExpenses();
                case 3 -> manager.showTotalByCategory();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }
}
