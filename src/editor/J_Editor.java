package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class J_Editor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tree View Sample");

        //primaryStage = FXMLLoader.load(getClass().getResource("startup.fxml"));
        //primaryStage = FXMLLoader.load(getClass().getResource("chatroom/chat-room.fxml"));
        primaryStage = FXMLLoader.load(getClass().getResource("juggernaut.fxml"));

        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
