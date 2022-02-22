package pers.lxl.mylearnproject.javase.io.nio.chat.chatclient;

import java.io.IOException;

public class BClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("xqm");
    }
}
