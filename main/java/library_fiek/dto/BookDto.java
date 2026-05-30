package library_fiek.dto;

public class BookDto {
    private int id;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private int quantity;
    private int availableQuantity;

    public BookDto(int id, String title, String author, String category, String isbn, int quantity, int availableQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
    }

    public BookDto(String title, String author, String category, String isbn, int quantity) {
        this(0, title, author, category, isbn, quantity, quantity);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}