package library_fiek.services;

import library_fiek.dto.BookDto;
import library_fiek.mappers.BookMapper;
import library_fiek.models.Book;
import library_fiek.repositories.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();
    private final BookMapper bookMapper = new BookMapper();

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }

        return bookRepository.search(keyword.trim());
    }

    public void create(BookDto dto) {
        validate(dto);
        Book book = bookMapper.toModel(dto);
        bookRepository.insert(book);
    }

    public void update(int id, BookDto dto) {
        validate(dto);
        Book book = bookMapper.toModel(id, dto);
        bookRepository.update(book);
    }

    public void delete(int id) {
        bookRepository.delete(id);
    }

    private void validate(BookDto dto) {
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required.");
        }

        if (dto.getAuthor() == null || dto.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author is required.");
        }

        if (dto.getCategory() == null || dto.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }

        if (dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }
}