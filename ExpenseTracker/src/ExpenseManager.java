import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private static final String FILE_NAME = "expenses.csv";

    public ExpenseManager() {
        loadExpenses();
    }

    public void addExpense(double amount, String category, String note, LocalDate date) {
        Expense expense = new Expense(amount, category, note, date);
        expenses.add(expense);
        saveExpenses();
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }
        System.out.println("\nDate | Category | Amount | Note");
        System.out.println("-----------------------------------------");
        expenses.forEach(System.out::println);
    }

    public void showTotalByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Expense e : expenses) {
            totals.put(e.getCategory(), totals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\nTotal Expense by Category:");
        for (var entry : totals.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                pw.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String note = parts[3];
                expenses.add(new Expense(amount, category, note, date));
            }
        } catch (Exception e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
