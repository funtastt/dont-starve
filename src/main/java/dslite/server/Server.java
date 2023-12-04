package dslite.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dslite.protocol.Message;
import dslite.protocol.MessageProtocol;

public class Server {
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<>();

    public void start() {
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                Client client = new Client(input, output, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Message message, Client sender) {
        for (Client reciever : clients) {
            if (reciever.equals(sender)) {
                continue;
            }

            try {
                byte[] encoded = MessageProtocol.encodeMessage(message);
                reciever.getOutput().write(encoded);
                reciever.getOutput().flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Server chatServer = new Server();
        chatServer.start();
    }

    static class Client implements Runnable {

        private DataInputStream input;
        private DataOutputStream output;
        private Server server;

        public Client(DataInputStream input, DataOutputStream output, Server chatServer) {
            this.input = input;
            this.output = output;
            this.server = chatServer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    byte[] data = new byte[1024];  // настройте размер по необходимости
                    int bytesRead = input.read(data);
                    if (bytesRead == -1) {
                        break;
                    }
                    Message message = MessageProtocol.decodeMessage(data);
                    server.sendMessage(message, this);
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
