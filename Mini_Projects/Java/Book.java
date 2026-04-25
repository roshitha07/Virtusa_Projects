class Book {
    private int id;
    private String title;
    private String author;
    private int availableCopies;

    public Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = copies;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getAvailableCopies() { return availableCopies; }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void issue() {
        availableCopies--;
    }

    public void returnBook() {
        availableCopies++;
    }
}
