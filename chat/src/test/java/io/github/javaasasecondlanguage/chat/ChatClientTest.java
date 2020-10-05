package io.github.javaasasecondlanguage.chat;

import io.github.javaasasecondlanguage.chat.client.ChatClient;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatClientTest {
    private static String MY_NAME_IN_CHAT = "I_CAN_COPY_PASTE";
    private static String MY_MESSAGE_TO_CHAT = "SOMEONE_KILL_ME";

    @Test
    public void login() throws IOException {
        Response response = ChatClient.login(MY_NAME_IN_CHAT);
        System.out.println("[" + response + "]");
        String body = response.body().string();
        System.out.println();
        assertTrue(response.code() == 200 || body.equals("Already logged in:("));
    }

    @Test
    public void viewChat() throws IOException {
        Response response = ChatClient.viewChat();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        assertEquals(200, response.code());
    }


    @Test
    public void viewOnline() throws IOException {
        Response response = ChatClient.viewOnline();
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        assertEquals(200, response.code());
    }

    @Test
    public void say() throws IOException {
        Response response = ChatClient.say(MY_NAME_IN_CHAT, MY_MESSAGE_TO_CHAT);
        System.out.println("[" + response + "]");
        System.out.println(response.body().string());
        assertEquals(200, response.code());
    }
}
