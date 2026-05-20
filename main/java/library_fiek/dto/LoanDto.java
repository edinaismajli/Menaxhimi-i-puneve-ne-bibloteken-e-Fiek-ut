package library_fiek.dto;
import java.time.LocalDate;

public class LoanDto {
    private int bookId;
    private int memberId;
    private LocalDate dueDate;

    public LoanDto(int bookId, int memberId, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.dueDate = dueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}