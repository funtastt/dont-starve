package dslite.ui.characteristics;

import javafx.scene.paint.Color;

import java.util.Locale;


// TODO:
public final class ElementaryStatisticsBox extends StatisticsBox {
    ElementaryStatisticsBox(String text) {
        super(text);
    }

    @Override
    protected void setValue(double val) {
        value.setText(String.format(Locale.ROOT, "%.0f", val));
        value.setTextFill(Color.WHITE);
    }
}
