package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Node;
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

        Parent root = FXMLLoader.load(getClass().getResource("juggernaut.fxml"));

        /*/////////////////////////////////////////////////////////////// To be implemented to editor
        TreeItem<String> rootItem = new TreeItem<String> ("Outliner");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Layer" + i);
            rootItem.getChildren().add(item);
        }
        TreeView<String> tree = new TreeView<String> (rootItem);
        StackPane root1 = new StackPane();
        root1.getChildren().add(tree);
        //primaryStage.setScene(new Scene(root1, 300, 250));
        //primaryStage.show();
        /////////////////////////////////////////////////////////////// */

        JE_Renderer.GetInstance().Run();

        primaryStage.setTitle("Juggernaut Engine [Editor]");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
