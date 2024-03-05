package org.example.section2;

import java.net.InetSocketAddress;

public class ServerConfiguration {
    private static ServerConfiguration serverConfiguration;

    private final InetSocketAddress inetSocketAddress;
    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.inetSocketAddress = new InetSocketAddress("localhost", port);
        this.greetingMessage = greetingMessage;

        if (serverConfiguration == null) {
            serverConfiguration = this;
        }
    }

    public static ServerConfiguration getInstance() {
        return serverConfiguration;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }
}
