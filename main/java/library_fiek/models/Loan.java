package library_fiek.models;

public class Loan {
    private int id;
    private int bookId;
    private int memberId;
    private String loanDate;
    private String dueDate;
    private String returnDate;
    private String status;

    public Loan(int id, int bookId, int memberId, String loanDate, String dueDate, String returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public String getLoanDate() { return loanDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public void setLoanDate(String loanDate) { this.loanDate = loanDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }
}