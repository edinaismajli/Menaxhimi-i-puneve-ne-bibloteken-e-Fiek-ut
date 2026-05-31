package library_fiek.controllers;

import library_fiek.dto.BookDto;
import library_fiek.models.Book;
import library_fiek.services.BookService;
import library_fiek.services.LanguageService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BooksController {
    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book, Integer> quantityColumn;

    @FXML
    private TableColumn<Book, Integer> availableColumn;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField searchField;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));

        MenuItem deleteItem = new MenuItem(LanguageService.get("context.deleteBook"));
        deleteItem.setOnAction(event -> deleteBook());

        ContextMenu contextMenu = new ContextMenu(deleteItem);
        booksTable.setContextMenu(contextMenu);

        loadBooks();
    }

    @FXML
    private void addBook() {
        try {
            BookDto dto = readBookDto();
            bookService.create(dto);
            clearFields();
            loadBooks();
        } catch (Exception e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void updateBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning(LanguageService.get("message.selectBook"));
            return;
        }

        try {
            BookDto dto = readBookDto();
            bookService.update(selected.getId(), dto);
            clearFields();
            loadBooks();
        } catch (Exception e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void deleteBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning(LanguageService.get("message.selectBook"));
            return;
        }

        bookService.delete(selected.getId());
        clearFields();
        loadBooks();
    }

    @FXML
    private void searchBooks() {
        booksTable.setItems(
                FXCollections.observableArrayList(bookService.search(searchField.getText()))
        );
    }

    @FXML
    private void fillFormFromSelection() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        titleField.setText(selected.getTitle());
        authorField.setText(selected.getAuthor());
        categoryField.setText(selected.getCategory());
        isbnField.setText(selected.getIsbn());
        quantityField.setText(String.valueOf(selected.getQuantity()));
    }

    private void loadBooks() {
        booksTable.setItems(
                FXCollections.observableArrayList(bookService.findAll())
        );
    }

    private BookDto readBookDto() {
        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LanguageService.get("message.quantityNumber"));
        }

        return new BookDto(
                titleField.getText(),
                authorField.getText(),
                categoryField.getText(),
                isbnField.getText(),
                quantity
        );
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        isbnField.clear();
        quantityField.clear();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LanguageService.get("alert.books"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}