package library_fiek.services;

import library_fiek.dto.LoanDto;
import library_fiek.models.Loan;
import library_fiek.repositories.LoanRepository;

import java.util.List;

public class LoanService {
    private final LoanRepository loanRepository = new LoanRepository();

    public List<Loan> findAll() {
        loanRepository.updateOverdueLoans();
        return loanRepository.findAll();
    }

    public void create(LoanDto dto) {
        validate(dto);
        loanRepository.insert(dto.getBookId(), dto.getMemberId(), dto.getDueDate());
    }

    public void markReturned(int id) {
        loanRepository.markReturned(id);
    }

    private void validate(LoanDto dto) {
        if (dto.getBookId() <= 0) {
            throw new IllegalArgumentException("Book ID must be greater than zero.");
        }

        if (dto.getMemberId() <= 0) {
            throw new IllegalArgumentException("Member ID must be greater than zero.");
        }

        if (dto.getDueDate() == null) {
            throw new IllegalArgumentException("Due date is required.");
        }
    }
}