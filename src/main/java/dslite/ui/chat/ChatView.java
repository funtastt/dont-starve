package dslite.ui.chat;

import dslite.client.Client;
import dslite.client.UserConfig;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;


public class ChatView extends VBox {

    private ScrollPane messageScrollPane;
    private TextArea conversation;
    private TextField input;

    private UserConfig userConfig;
    private static ChatView chatView;
    private Client client;

    private final EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                String username = getUserConfig().getUsername();
                String message = input.getText() + "\n";

                getClient().sendMessage(username + ": " + message);

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

        client = new Client();

        chatView = this;
    }

    public void startChat() {
        client.start();
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public static ChatView getChatView() {
        return chatView;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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