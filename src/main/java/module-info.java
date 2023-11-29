module com.example.exam_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports dslite.controllers;
    exports dslite;

    opens dslite.controllers to javafx.fxml;
}