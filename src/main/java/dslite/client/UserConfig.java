package dslite.client;

public class UserConfig {

    private String username;
    private String host;
    private int port;

    public UserConfig(String username, String host, int port) {
        this.username = username;
        this.host = host;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
