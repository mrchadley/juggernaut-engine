package editor.chatroom;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread
{
    private Socket clientSocket;

    private Scanner input;
    private PrintWriter output;

    private TextArea chat;

    public ClientThread(String ip, String name)
    {
        try {
            clientSocket = new Socket(ip, 8080);
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat-room.fxml"));

            Stage stage = loader.load();
            J_ChatController chatController = loader.<J_ChatController>getController();

            chatController.name = name;
            chatController.serverFlag = false;
            chatController.client = this;
            chat = chatController.GetChat();

            stage.setTitle("JuggernautChat<Client> - " + name);
            stage.show();
            this.start();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public void SendMessage(String msg)
    {
        output.println(msg);
        output.flush();
    }
    @Override public void run()
    {
        while(true)
        {
            if(input.hasNextLine())
            {
                String msg = input.nextLine();
                chat.setText(chat.getText() + msg + "\n");
            }
        }
    }
}
