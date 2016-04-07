package engine;

import javafx.stage.Stage;


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

        engine.Initialize(name, width, height, fps, primaryStage);
        engine.Run();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
