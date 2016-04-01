package editor.chatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class J_ChatController
{
    public String name = "blah";
    public String ip;

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
                if(event.getCode() == KeyCode.ENTER)
                    SendMessage(null);
            }
        });
    }

    public void SendMessage(ActionEvent actionEvent)
    {
        if(textField.getText().intern() != "")
        {
            textArea.setText(textArea.getText() + name + ": " + textField.getText() + "\n");
            textField.clear();
        }
    }
}
