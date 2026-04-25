import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Transaction {
    private Book book;
    private User user;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public Transaction(Book book, User user) {
        this.book = book;
        this.user = user;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(7);
    }

    public void returnBook() {
        returnDate = LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

        if (daysLate > 0) {
            fine = daysLate * 5;
        }
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public Book getBook() { return book; }
    public User getUser() { return user; }
    public LocalDate getDueDate() { return dueDate; }
    public double getFine() { return fine; }
}