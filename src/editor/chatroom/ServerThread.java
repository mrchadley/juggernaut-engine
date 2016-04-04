package editor.chatroom;


import javafx.scene.control.TextArea;

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

    public ServerThread(TextArea text)
    {
        chat = text;
    }
    public void SendMessage(String msg)
    {
        output.println(msg);
        output.flush();
    }
    @Override public void run()
    {
        System.out.println("starting server");

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
                chat.setText(chat.getText() + msg);

                output.println(msg);
                output.flush();
            }
        }
    }
}
