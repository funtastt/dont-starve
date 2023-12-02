package dslite.ui.chat;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import static dslite.ui.chat.ChatApplication.getChatApplication;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;


public class ChatView extends VBox {

    private ScrollPane messageScrollPane;
    private TextArea conversation;
    private TextField input;

    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String username = getChatApplication().getUserConfig().getUsername();
                String message = input.getText() + "\n";

                getChatApplication().getClient().sendMessage(username + ": " + message);

                conversation.appendText(username + " (you): " + message);

                input.clear();
                event.consume();
            }
        }
    };

    public ChatView() {
        super();
        initMessageScrollPane();
        initInputTextField();

        setSpacing(10.0);
        setPrefSize(300.0, 400.0);
        setMaxSize(300.0, 400.0);
        setFocusTraversable(false);
        getChildren().addAll(messageScrollPane, input);
    }

    private void initMessageScrollPane() {
        messageScrollPane = new ScrollPane();
        messageScrollPane.setPrefSize(300.0, 300.0);
        messageScrollPane.setMaxSize(300.0, 300.0);

        conversation = new TextArea();
        conversation.setEditable(false);
        messageScrollPane.setContent(conversation);
    }

    private void initInputTextField() {
        input = new TextField();
        input.setPrefSize(250.0, 30.0);
        input.setMaxSize(250.0, 30.0);
        input.setFocusTraversable(true);
        input.addEventHandler(KEY_PRESSED, eventHandler);
    }

    public void appendMessage(String message) {
        if (message != null) {
            conversation.appendText(message);
        }
    }

}