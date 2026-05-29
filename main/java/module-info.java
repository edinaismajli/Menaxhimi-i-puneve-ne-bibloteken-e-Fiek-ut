module library_fiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens library_fiek.application to javafx.fxml;
    opens library_fiek.controllers to javafx.fxml;
    opens library_fiek.models to javafx.base;
    opens library_fiek.dto to javafx.base;
    opens library_fiek.enums to javafx.base;

    exports library_fiek.application;
    exports library_fiek.controllers;
    exports library_fiek.models;
    exports library_fiek.dto;
    exports library_fiek.enums;
    exports library_fiek.services;
    exports library_fiek.utilities;
}