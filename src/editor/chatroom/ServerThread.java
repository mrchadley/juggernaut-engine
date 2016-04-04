package editor.chatroom;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread
{
    private Socket clientSocket;

    private Scanner input;
    private PrintWriter output;

    private TextArea chat;

    public ServerThread(String name)
    {
        try
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat-room.fxml"));

            Stage stage = loader.load();
            J_ChatController chatController = loader.<J_ChatController>getController();

            chatController.name = name;
            chatController.serverFlag = true;
            chatController.server = this;
            chat = chatController.GetChat();

            stage.setTitle("JuggernautChat<Server> - " + name);
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
        chat.setText(chat.getText() + msg + "\n");
        output.println(msg);
        output.flush();
    }
    @Override public void run()
    {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            clientSocket = serverSocket.accept();

            input = new Scanner(clientSocket.getInputStream());
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        while(true)
        {
            if(input.hasNextLine())
            {
                String msg = input.nextLine();
                chat.setText(chat.getText() + msg + "\n");

                output.println(msg);
                output.flush();
            }
        }
    }
}
