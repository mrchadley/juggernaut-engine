package editor.chatroom;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ChatServer implements Runnable
{
    private ServerSocket serverSocket = null;
    private Socket connectionBuffer = null;
    private boolean running = true;
    private TextArea chat;

    public ChatServer(int port, TextArea textArea)
    {
        chat = textArea;
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        chat.setText("Chat server started.");
        while(running)
        {
            waitForConnections();
        }
    }


    private void waitForConnections()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for Connections");
                connectionBuffer = serverSocket.accept();

                System.out.println("Connection Established");
                ChatThread newConnection = new ChatThread(connectionBuffer, chat);
                newConnection.run();
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}


