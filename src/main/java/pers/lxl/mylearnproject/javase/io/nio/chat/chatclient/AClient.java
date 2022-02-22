package pers.lxl.mylearnproject.javase.io.nio.chat.chatclient;

import java.io.IOException;

public class AClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("lxl");
    }
}
