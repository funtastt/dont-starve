package dslite.ui.menu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public final class Arrow extends ImageView {

    private final boolean previous;
    private static final String PATH = "/dslite/ui/play_inactive.png";
    private static final Image ARROW_IMAGE = loadImage(PATH);
    private static final double FIT_WIDTH = 30;
    private static final double FIT_HEIGHT = 40;

    public Arrow(boolean isPrevious) {
        super();
        setFitWidth(FIT_WIDTH);
        setFitHeight(FIT_HEIGHT);
        setImage(ARROW_IMAGE);
        previous = isPrevious;
        if (isPrevious) { setRotate(180.0); }
    }

    public void linkToButton(ArrowButton btn) {
        this.setOnMouseClicked(mouseEvent ->
                btn.setText(isPrevious() ? btn.getPreviousOption() : btn.getNextOption()));
    }

    public boolean isPrevious() {
        return previous;
    }

    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(Arrow.class.getResource(path)).toExternalForm());
    }

}
