package library_fiek.controllers;

import library_fiek.dto.LoanDto;
import library_fiek.models.Loan;
import library_fiek.services.LoanService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class LoansController {
    @FXML
    private TableView<Loan> loansTable;

    @FXML
    private TableColumn<Loan, Integer> idColumn;

    @FXML
    private TableColumn<Loan, Integer> bookIdColumn;

    @FXML
    private TableColumn<Loan, Integer> memberIdColumn;

    @FXML
    private TableColumn<Loan, String> loanDateColumn;

    @FXML
    private TableColumn<Loan, String> dueDateColumn;

    @FXML
    private TableColumn<Loan, String> returnDateColumn;

    @FXML
    private TableColumn<Loan, String> statusColumn;

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private DatePicker dueDatePicker;

    private final LoanService loanService = new LoanService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        MenuItem returnItem = new MenuItem("Return selected loan");
        returnItem.setOnAction(event -> returnLoan());

        ContextMenu contextMenu = new ContextMenu(returnItem);
        loansTable.setContextMenu(contextMenu);

        loadLoans();
    }

    @FXML
    private void addLoan() {
        try {
            LoanDto dto = readLoanDto();
            loanService.create(dto);
            clearFields();
            loadLoans();
        } catch (Exception e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void returnLoan() {
        Loan selected = loansTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning("Select a loan first.");
            return;
        }

        loanService.markReturned(selected.getId());
        loadLoans();
    }

    private void loadLoans() {
        loansTable.setItems(
                FXCollections.observableArrayList(loanService.findAll())
        );
    }

    private LoanDto readLoanDto() {
        int bookId;
        int memberId;

        try {
            bookId = Integer.parseInt(bookIdField.getText());
            memberId = Integer.parseInt(memberIdField.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Book ID and Member ID must be numbers.");
        }

        return new LoanDto(
                bookId,
                memberId,
                dueDatePicker.getValue()
        );
    }

    private void clearFields() {
        bookIdField.clear();
        memberIdField.clear();
        dueDatePicker.setValue(null);
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Loans");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}