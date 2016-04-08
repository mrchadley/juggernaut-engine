package engine;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class TestGame extends J_Game
{
    private J_Level level;
    private static J_Game game;
    public static void LaunchGame(J_Level level)
    {
        game = new TestGame();
        game.start(new Stage());
        game.GetEngine().SetLevel(level);
    }
    @Override
    public void start(Stage primaryStage)
    {
        //load config

        level = Load();
        try
        {
            level.SaveLevel(new File("test.jLevel"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        engine.Initialize(name, width, height, fps, primaryStage);
        engine.SetLevel(level);
        engine.Run();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
