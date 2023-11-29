module com.example.exam_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports dslite.controllers;
    exports dslite.gui.menu;
    exports dslite.world;
    exports dslite.player;
    exports dslite.interfaces;
    exports dslite;

    opens dslite.controllers to javafx.fxml;
}