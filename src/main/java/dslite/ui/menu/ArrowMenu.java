package dslite.ui.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public final class ArrowMenu extends HBox {
    private final ArrowButton btn = new ArrowButton();
    private static final double MENU_WIDTH = 190.0;
    private static final double MENU_HEIGHT = 50.0;
    private static final double SPACING = 5.0;

    public ArrowMenu() {
        super();
        setAlignment(Pos.CENTER);
        setWidth(MENU_WIDTH);
        setHeight(MENU_HEIGHT);
        setSpacing(SPACING);

        Arrow prev = new Arrow(true);
        prev.linkToButton(btn);
        Arrow next = new Arrow(false);
        next.linkToButton(btn);
        getChildren().addAll(prev, btn, next);
    }

    public ArrowButton getMenuButton() {
        return btn;
    }
}
