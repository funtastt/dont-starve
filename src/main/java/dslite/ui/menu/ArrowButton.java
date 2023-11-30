package dslite.ui.menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public final class ArrowButton<T> extends Button {

    private ObservableList<T> options = FXCollections.observableArrayList();
    private static final double MIN_WIDTH = 150.0;
    private static final double MIN_HEIGHT = 50.0;
    private int selectedOption;

    public ArrowButton() {
        super();
        setMinWidth(MIN_WIDTH);
        setMinHeight(MIN_HEIGHT);
        setFocusTraversable(false);
    }

    public T getPreviousOption() {
        return (selectedOption > 0) ? options.get(--selectedOption) : options.get(selectedOption);
    }

    public T getNextOption() {
        return (selectedOption < options.size() - 1) ? options.get(++selectedOption) : options.get(selectedOption);
    }

    public void setSelectedOption(int index) {
        selectedOption = index;
        setText(options.get(index).toString());
    }

    public void setOptions(ObservableList<T> options) {
        this.options = options;
        setSelectedOption(0);
    }
}
