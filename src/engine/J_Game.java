package engine;

import editor.J_Level;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import juggernaut_engine.J_Log;
import juggernaut_engine.framework.Sprite;
import juggernaut_engine.framework.Vector2;
import juggernaut_engine.framework.basic_shapes.Oval;
import juggernaut_engine.framework.basic_shapes.Rectangle;

import java.util.LinkedList;

public abstract class J_Game extends Application
{
    String name = "";
    int width = 800;
    int height = 600;
    int fps = 30;
    LinkedList<J_Level> levels = new LinkedList<>();
    J_Engine engine = J_Engine.GetInstance();

    @Override
    public void start(Stage primaryStage)
    {
        //load config
        J_Level testLevel = new J_Level();
        testLevel.AddObject(new Rectangle(new Vector2(200, 200), new Vector2(400, 100), Color.RED));
        testLevel.AddObject(new Oval(new Vector2(500, 300), new Vector2(200, 200), Color.PURPLE));
        Sprite testSprite = new Sprite(new Vector2(50, 50), new Vector2(320, 200), new Image("juggernaut_engine/images/PhandroidRobotPlain.PNG"));
        testSprite.setVelocity(new Vector2(0, 50));
        testLevel.AddObject(testSprite);
        levels.add(testLevel);

        //Initialize, SetLevel, Run,J_Engine
        engine.Initialize(name, width, height, fps, primaryStage);
        engine.SetLevel(testLevel);
        engine.Run();
    }

    public void Load(String config)
    {
        //fileIO stuff for loading the name, width, height, fps, and level files
        //fileIO stuff for loading each level
        //add each level to the list of levels
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
