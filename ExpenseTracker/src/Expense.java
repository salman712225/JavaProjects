import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    private double amount;
    private String category;
    private String note;
    private LocalDate date;

    public Expense(double amount, String category, String note, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | %s", date, category, amount, note);
    }

    public String toCSV() {
        return date + "," + category + "," + amount + "," + note;
    }
}
