module com.example.exam_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports dslite;
    exports dslite.controllers;
    exports dslite.ui.menu;
    exports dslite.world;
    exports dslite.player;
    exports dslite.interfaces;

    opens dslite.controllers to javafx.fxml;
    exports dslite.enums;
}