package editor;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by austin on 31/03/16.
 */
public class FontFamily extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("TRON Synopsis");

        // load the tron font.
        Font.loadFont(
                FontFamily.class.getResource("redseven.TTF").toExternalForm(),
                10
        );
    }
}