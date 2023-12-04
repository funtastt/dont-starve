package dslite.client;

import java.io.*;
import java.net.Socket;

import dslite.protocol.Message;
import dslite.protocol.MessageType;
import dslite.protocol.MessageProtocol;

import static dslite.ui.chat.ChatView.getChatView;

public class Client {

    private Socket socket;
    private ClientThread thread;

    public void sendMessage(String content) {
        Message message = new Message(MessageType.CHAT, content);
        try {
            byte[] encodedMessage = MessageProtocol.encodeMessage(message);
            thread.getOutput().write(encodedMessage);
            thread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        String username = getChatView().getUserConfig().getUsername();
        String host = getChatView().getUserConfig().getHost();
        int port = getChatView().getUserConfig().getPort();

        DataInputStream input;
        DataOutputStream output;
        try {
            socket = new Socket(host, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        thread = new ClientThread(input, output, this);
        new Thread(thread).start();

        sendMessage("Hello! " + username + " just joined us!\n");
    }

    static class ClientThread implements Runnable {

        private DataInputStream input;
        private DataOutputStream output;
        private Client chatClient;

        public ClientThread(DataInputStream input, DataOutputStream output, Client chatClient) {
            this.input = input;
            this.output = output;
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    byte[] data = new byte[1024];
                    int bytesRead = input.read(data);
                    if (bytesRead == -1) {
                        break;
                    }
                    Message message = MessageProtocol.decodeMessage(data);
                    getChatView().appendMessage((String) message.getContent());
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public DataOutputStream getOutput() {
            return output;
        }
    }
}
