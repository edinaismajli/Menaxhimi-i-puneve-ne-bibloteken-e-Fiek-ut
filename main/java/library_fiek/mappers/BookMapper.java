package library_fiek.mappers;

import library_fiek.dto.BookDto;
import library_fiek.models.Book;

public class BookMapper {
    public BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }

        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getIsbn(),
                book.getQuantity()
        );
    }

    public Book toModel(BookDto dto) {
        if (dto == null) {
            return null;
        }

        return new Book(
                0,
                dto.getTitle(),
                dto.getAuthor(),
                dto.getCategory(),
                dto.getIsbn(),
                dto.getQuantity(),
                dto.getQuantity()
        );
    }
}