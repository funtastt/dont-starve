module com.example.exam_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports dslite;
    exports dslite.ui.characteristics;
    exports dslite.ui.menu;
    exports dslite.ui.tiles;
    exports dslite.world;
    exports dslite.world.map;
    exports dslite.player;
    exports dslite.utils.interfaces;
    exports dslite.utils.enums;
    exports dslite.world.entity.mining;
    exports dslite.world.entity;
    exports dslite.world.entity.resouces;
    exports dslite.world.entity.picked;

    exports dslite.world.entity.generators;
    exports dslite.controllers;
    opens dslite.controllers to javafx.fxml;
    exports dslite.ui.chat;
    exports dslite.server;
    exports dslite.client;
}