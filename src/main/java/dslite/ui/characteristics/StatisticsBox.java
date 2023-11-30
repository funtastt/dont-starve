package dslite.ui.characteristics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Locale;

public class StatisticsBox extends HBox {
    protected final Label label;
    protected final Label value;
    private double maxVal;

    private static final Color GREEN = new Color(0.0, 1.0, 0.0, 1.0);
    private static final double MAX_HUE = GREEN.getHue();
    private static final BackgroundFill BG_FILL = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);

    protected StatisticsBox(String text) {
        super();
        maxVal = 0.0f;
        prefWidth(100.0);
        prefHeight(100.0);

        label = new Label(text);
        label.setFont(Font.font("Arial", 32.0));
        label.setTextFill(Color.WHITE);
        label.setBackground(new Background(BG_FILL));
        label.setAlignment(Pos.CENTER_LEFT);

        value = new Label();
        value.setFont(Font.font("Arial", 32.0));
        value.setBackground(new Background(BG_FILL));
        value.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(label, value);
    }

    protected StatisticsBox(String text, double maxVal) {
        this(text);
        this.maxVal = maxVal;
    }

    protected void setValue(double val) {
        value.setText(String.format(Locale.ROOT, "%.1f", val));
        value.setTextFill(Color.hsb(Math.max(MAX_HUE * val / maxVal, 0), 1.0, 1.0));
    }

}
