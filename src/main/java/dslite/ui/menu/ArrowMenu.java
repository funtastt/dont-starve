package dslite.ui.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public final class ArrowMenu extends HBox {
    private final ArrowButton btn = new ArrowButton();

    public ArrowMenu() {
        super();
        setAlignment(Pos.CENTER);
        setWidth(190.0);
        setHeight(50.0);
        setSpacing(5.0);

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
