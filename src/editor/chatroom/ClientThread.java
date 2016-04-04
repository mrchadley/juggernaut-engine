package editor.chatroom;


import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread
{
    private Socket clientSocket;

    private Scanner input;
    private PrintWriter output;

    private TextArea chat;

    public ClientThread(String ip, TextArea text)
    {
        try {
            clientSocket = new Socket(ip, 8080);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        chat = text;

        System.out.println("initializing client");

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
                chat.setText(chat.getText() + msg);
            }
        }
    }
}
