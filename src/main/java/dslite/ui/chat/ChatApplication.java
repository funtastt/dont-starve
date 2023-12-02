package dslite.ui.chat;

import dslite.client.Client;
import dslite.client.UserConfig;

public class ChatApplication {
    private UserConfig userConfig;
    private final ChatView chatView;
    private Client client;
    private static ChatApplication chatApplication;

    public ChatApplication() {
        setChatApplication(this);
        chatView = new ChatView();

        client = new Client(this);
    }


    public static ChatApplication getChatApplication() {
        if (chatApplication != null) {
            return chatApplication;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setChatApplication(ChatApplication chatApplication) {
        ChatApplication.chatApplication = chatApplication;
    }

    public void appendMessage(String message) {
        chatView.appendMessage(message);
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

    public ChatView getChatView() {
        return chatView;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
