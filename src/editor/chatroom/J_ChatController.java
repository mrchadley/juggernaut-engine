package editor.chatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class J_ChatController
{
    public String name = "blah";

    public boolean serverFlag = false;

    public Socket socket;
    public ServerSocket server;

    public PrintWriter output;
    public BufferedReader input;

    @FXML
    TextField textField;
    @FXML
    TextArea textArea;

    public void initialize()
    {
        textArea.setWrapText(true);
        textField.requestFocus();
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //if(event.getCode() == KeyCode.ENTER)
                    //SendMessage(null);
            }
        });
    }
    public void StartChatServer() throws IOException
    {
        server = new ServerSocket(8080);
        socket = server.accept();
        ChatThread thread = new ChatThread(socket, textArea);
        thread.run();
    }

}
