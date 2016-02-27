package juggernaut_engine;

import javafx.stage.Stage;

/**
 * Created by 100555250 on 2/21/2016.
 */
public class Test extends J_Engine {

    @Override
    public void start(Stage primaryStage)
    {
        Startup("Test", 1280, 720, 60);
        Run(primaryStage);
        primaryStage.show();
    }

    @Override
    public void Shutdown() {

    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
