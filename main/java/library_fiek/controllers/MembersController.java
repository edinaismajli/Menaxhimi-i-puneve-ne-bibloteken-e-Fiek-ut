package library_fiek.controllers;

import library_fiek.dto.MemberDto;
import library_fiek.enums.MemberType;
import library_fiek.models.Member;
import library_fiek.services.MemberService;
import library_fiek.services.LanguageService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MembersController {
    @FXML
    private TableView<Member> membersTable;

    @FXML
    private TableColumn<Member, Integer> idColumn;

    @FXML
    private TableColumn<Member, String> nameColumn;

    @FXML
    private TableColumn<Member, String> emailColumn;

    @FXML
    private TableColumn<Member, String> phoneColumn;

    @FXML
    private TableColumn<Member, String> typeColumn;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> memberTypeCombo;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("memberType"));

        memberTypeCombo.setItems(
                FXCollections.observableArrayList(
                        MemberType.STUDENT.name(),
                        MemberType.STAFF.name()
                )
        );
        memberTypeCombo.getSelectionModel().selectFirst();

        MenuItem deleteItem = new MenuItem(LanguageService.get("context.deleteMember"));
        deleteItem.setOnAction(event -> deleteMember());

        ContextMenu contextMenu = new ContextMenu(deleteItem);
        membersTable.setContextMenu(contextMenu);

        loadMembers();
    }

    @FXML
    private void addMember() {
        try {
            MemberDto dto = readMemberDto();
            memberService.create(dto);
            clearFields();
            loadMembers();
        } catch (Exception e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void updateMember() {
        Member selected = membersTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning(LanguageService.get("message.selectMember"));
            return;
        }

        try {
            MemberDto dto = readMemberDto();
            memberService.update(selected.getId(), dto);
            clearFields();
            loadMembers();
        } catch (Exception e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void deleteMember() {
        Member selected = membersTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning(LanguageService.get("message.selectMember"));
            return;
        }

        memberService.delete(selected.getId());
        clearFields();
        loadMembers();
    }

    @FXML
    private void searchMembers() {
        membersTable.setItems(
                FXCollections.observableArrayList(memberService.search(searchField.getText()))
        );
    }

    @FXML
    private void fillFormFromSelection() {
        Member selected = membersTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        fullNameField.setText(selected.getFullName());
        emailField.setText(selected.getEmail());
        phoneField.setText(selected.getPhone());
        memberTypeCombo.setValue(selected.getMemberType());
    }

    private void loadMembers() {
        membersTable.setItems(
                FXCollections.observableArrayList(memberService.findAll())
        );
    }

    private MemberDto readMemberDto() {
        return new MemberDto(
                fullNameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                memberTypeCombo.getValue()
        );
    }

    private void clearFields() {
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        memberTypeCombo.getSelectionModel().selectFirst();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LanguageService.get("alert.members"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}