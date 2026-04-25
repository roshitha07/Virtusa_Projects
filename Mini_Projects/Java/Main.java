import java.util.*;

public class Main {

    static Map<Integer, Book> books = new HashMap<>();
    static Map<Integer, User> users = new HashMap<>();
    static List<Transaction> transactions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== LIBRARY SYSTEM =====");

        while (true) {
            System.out.println("\n1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");

            int choice = getInt();

            if (choice == 1) adminPanel();
            else if (choice == 2) userPanel();
            else break;
        }
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n--- ADMIN PANEL ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Register User");
            System.out.println("5. View Books");
            System.out.println("6. Back");

            int choice = getInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: removeBook(); break;
                case 3: updateBook(); break;
                case 4: registerUser(); break;
                case 5: viewBooks(); break;
                case 6: return;
            }
        }
    }

    static void userPanel() {

        System.out.print("Enter User ID: ");
        int userId = getInt();

        if (!users.containsKey(userId)) {
            System.out.println("User not found");
            return;
        }

        while (true) {
            System.out.println("\n--- USER PANEL ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Search Book");
            System.out.println("4. Logout");

            int choice = getInt();

            switch (choice) {
                case 1: issueBook(userId); break;
                case 2: returnBook(userId); break;
                case 3: searchBook(); break;
                case 4: return;
            }
        }
    }

    static int getInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static void addBook() {
        sc.nextLine();

        System.out.print("Enter Book ID: ");
        int id = getInt(); sc.nextLine();

        if (books.containsKey(id)) {
            System.out.println("Already exists");
            return;
        }

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        System.out.print("Enter no.of Copies: ");
        int copies = getInt();

        books.put(id, new Book(id, title, author, copies));

        System.out.println("Book added");
    }

    static void removeBook() {
        System.out.print("Enter Book ID: ");
        int id = getInt();

        if (books.remove(id) != null)
            System.out.println("Book is Removed");
        else
            System.out.println("Not found");
    }

    static void updateBook() {
        sc.nextLine();

        System.out.print("Enter Book ID: ");
        int id = getInt(); sc.nextLine();

        if (!books.containsKey(id)) {
            System.out.println("Not found");
            return;
        }

        System.out.print("Enter New Title: ");
        String title = sc.nextLine();

        System.out.print("Enter New Author: ");
        String author = sc.nextLine();

        System.out.print("Enter New Copies: ");
        int copies = getInt();

        books.put(id, new Book(id, title, author, copies));

        System.out.println("Updated");
    }

    static void viewBooks() {
        for (Book b : books.values()) {
            System.out.println(
                b.getId() + " | " +
                b.getTitle() + " | " +
                b.getAuthor() + " | Available: " + b.getAvailableCopies()
            );
        }
    }

    static void registerUser() {
        sc.nextLine();

        System.out.print("Enter User ID: ");
        int id = getInt(); sc.nextLine();

        if (users.containsKey(id)) {
            System.out.println("User already exists");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        users.put(id, new User(id, name));

        System.out.println("User registered");
    }

    static void issueBook(int userId) {
        System.out.print("Enter Book ID: ");
        int bookId = getInt();

        Book book = books.get(bookId);

        if (book == null || !book.isAvailable()) {
            System.out.println("Not available");
            return;
        }

        Transaction t = new Transaction(book, users.get(userId));
        transactions.add(t);
        book.issue();

        System.out.println("Issued. Due: " + t.getDueDate());
    }

    static void returnBook(int userId) {
        System.out.print("Enter Book ID: ");
        int bookId = getInt();

        for (Transaction t : transactions) {
            if (t.getBook().getId() == bookId &&
                t.getUser().getId() == userId &&
                !t.isReturned()) {

                t.returnBook();
                t.getBook().returnBook();

                System.out.println("Returned. Fine: " + t.getFine());
                return;
            }
        }

        System.out.println("No record found");
    }

    static void searchBook() {
        sc.nextLine();

        System.out.print("Enter Search Term: ");
        String key = sc.nextLine().toLowerCase();

        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(key) ||
                b.getAuthor().toLowerCase().contains(key)) {

                System.out.println(
                    b.getId() + " | " +
                    b.getTitle() + " | " +
                    b.getAuthor() + " | Available: " + b.getAvailableCopies()
                );
            }
        }
    }
}