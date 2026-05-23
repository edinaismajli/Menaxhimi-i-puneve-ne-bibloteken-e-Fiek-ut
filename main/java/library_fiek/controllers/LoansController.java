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

    }