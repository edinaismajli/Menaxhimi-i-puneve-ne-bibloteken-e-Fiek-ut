package library_fiek.dto;

public class BookDto {
    private String title;
    private String author;
    private String category;
    private String isbn;
    private int quantity;

    public BookDto(String title, String author, String category, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.quantity = quantity;
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
}