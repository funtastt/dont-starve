module com.example.exam_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports dslite;
    exports dslite.views;
    exports dslite.ui.characteristics;
    exports dslite.ui.menu;
    exports dslite.ui.tiles;
    exports dslite.world;
    exports dslite.world.map;
    exports dslite.player;
    exports dslite.interfaces;
    exports dslite.enums;

    opens dslite.views to javafx.fxml;
    exports dslite.world.entity.mining_res;
    exports dslite.world.entity;
}