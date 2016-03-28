package engine;

import engine.game_objects.*;
import engine.game_objects.render_components.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.framework.Vector2;

import java.io.IOException;
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
        J_Level testLevel = Load();

        try
        {
            testLevel.SaveLevel("test");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        J_Level loadTestLevel = new J_Level();

        try {
            loadTestLevel.LoadLevel("test");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }

        //Initialize, SetLevel, Run,J_Engine
        engine.Initialize(name, width, height, fps, primaryStage);
        engine.SetLevel(loadTestLevel);
        engine.Run();
    }

    public J_Level Load()
    {
        //fileIO stuff for loading the name, width, height, fps, and level files
        //fileIO stuff for loading each level
        //add each level to the list of levels

        J_Level level = new J_Level();

        J_GameObject oval = new J_GameObject("Oval", new J_Transform(new Vector2(50, 50), new Vector2(50, 50)));
        oval.AddComponent(new J_OvalRenderer(oval.GetTransform(), Color.BLACK, Color.RED));

        J_GameObject rect = new J_GameObject("Rect", new J_Transform(new Vector2(250, 50), new Vector2(150, 50)));
        rect.AddComponent(new J_RectangleRenderer(rect.GetTransform(), Color.GREEN, Color.ORANGE));

        J_GameObject sprite = new J_GameObject("Sprite", new J_Transform(new Vector2(250, 500), new Vector2(320, 200)));
        sprite.AddComponent(new J_SpriteRenderer(sprite.GetTransform(), new Image("/engine/images/PhandroidRobotPlain.PNG")));

        J_GameObject animatedSprite = new J_GameObject("animatedSprite", new J_Transform(new Vector2(500, 300), new Vector2(180, 250)));


        J_SpriteAnimation testAnim = new J_SpriteAnimation(new Image("/engine/images/spritesheet.png"), new Vector2(180, 250), 10, 0.032f);
        testAnim.SetLoopable(true);
        testAnim.SetOffset(-4.0f);
        J_SpriteAnimator animator = new J_SpriteAnimator(animatedSprite.GetTransform());
        animator.AddAnimation(testAnim);
        animator.PlayAnimation(0);

        animatedSprite.AddComponent(animator);

        level.AddObject(oval);
        level.AddObject(rect);
        level.AddObject(sprite);
        level.AddObject(animatedSprite);



        return level;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
