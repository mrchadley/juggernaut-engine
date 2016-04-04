package editor.chatroom;


import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatThread extends Thread
{
    private Socket clientSocket;

    private Scanner input;
    private PrintWriter output;

    private TextArea chat;

    public ChatThread(Socket connection, TextArea text)
    {
        super(connection.toString());
        clientSocket = connection;
        chat = text;

        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintWriter(clientSocket.getOutputStream(), true);
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
                //print msg to server screen
                chat.setText(chat.getText() + msg);
                output.println(msg);
                output.flush();
            }
        }
    }
}
