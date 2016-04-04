package editor.chatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sun.awt.windows.WingDings;

import java.io.IOException;


public class J_ChatController
{
    public String name = "blah";
    public String ip;

    public boolean serverFlag = false;

    public ClientThread client;
    public ServerThread server;


    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    Stage stage;

    public void initialize() throws IOException
    {
        textArea.setWrapText(true);
        textField.requestFocus();
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                    SendMessage(null);
            }
        });
    }

    public void SendMessage(ActionEvent actionEvent)
    {
        String msg = textField.getText();

        if(serverFlag)
            server.SendMessage(name + ": " + msg);
        else
            client.SendMessage(name + ": " + msg);
        textField.clear();
    }

    public TextArea GetChat()
    {
        return textArea;
    }
}
