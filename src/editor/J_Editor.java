package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class J_Editor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tree View Sample");

        //primaryStage = FXMLLoader.load(getClass().getResource("startup.fxml"));
        //primaryStage = FXMLLoader.load(getClass().getResource("chat-room.fxml"));
        primaryStage = FXMLLoader.load(getClass().getResource("juggernaut.fxml"));

        //JE_Renderer.GetInstance().Run();


        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
