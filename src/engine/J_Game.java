package engine;

import engine.game_objects.*;
import engine.game_objects.render_components.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.framework.Vector2;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public abstract class J_Game extends Application
{
    String name = "";
    int width = 1280;
    int height = 720;
    int fps = 30;
    LinkedList<J_Level> levels = new LinkedList<>();
    J_Engine engine = J_Engine.GetInstance();

    @Override
    public void start(Stage primaryStage)
    {


        //Initialize, SetLevel, Run,J_Engine

    }

    public J_Level Load()
    {
        //fileIO stuff for loading the name, width, height, fps, and level files
        //fileIO stuff for loading each level
        //add each level to the list of levels

        J_Level level = new J_Level();

        J_GameObject oval = new J_GameObject("TestOval", new J_Transform(new Vector2(50, 50), new Vector2(50, 50)));
        oval.SetRenderer(new J_OvalRenderer(oval.GetTransform(), Color.BLACK, Color.RED));
        J_InputBinder binder = new J_InputBinder();
        binder.SetTransform(oval.GetTransform());
        binder.AddKey(KeyCode.W, new Vector2(30, 0));
        oval.AddComponent(binder);

        J_GameObject rect = new J_GameObject("Rect", new J_Transform(new Vector2(250, 50), new Vector2(150, 50)));
        rect.SetRenderer(new J_RectangleRenderer(rect.GetTransform(), Color.GREEN, Color.ORANGE));

        J_GameObject sprite = new J_GameObject("Sprite", new J_Transform(new Vector2(250, 500), new Vector2(320, 200)));
        sprite.SetRenderer(new J_SpriteRenderer(sprite.GetTransform(), new Image("/engine/images/PhandroidRobotPlain.PNG")));

        J_GameObject animatedSprite = new J_GameObject("animatedSprite", new J_Transform(new Vector2(500, 300), new Vector2(180, 250)));


        J_SpriteAnimation testAnim = new J_SpriteAnimation(new Image("/engine/images/spritesheet.png"), new Vector2(180, 250), 10, 0.032f);
        testAnim.SetLoopable(true);
        testAnim.SetOffset(-4.0f);
        J_SpriteAnimator animator = new J_SpriteAnimator(animatedSprite.GetTransform());
        animator.AddAnimation(testAnim);








        //J_GameObject animatedSprite2 = new J_GameObject("animatedSprite2", new J_Transform(new Vector2(900, 500), new Vector2(184, 312)));


        J_SpriteAnimation testAnim2 = new J_SpriteAnimation(new Image("/engine/images/spritesheet2.png"), new Vector2(184, 312), 8, 0.032f);
        testAnim2.SetLoopable(true);
        //testAnim.SetOffset(-4.0f);
        //J_SpriteAnimator animator2 = new J_SpriteAnimator(animatedSprite.GetTransform());
        animator.AddAnimation(testAnim2);
        //animator2.PlayAnimation(0);

        animator.PlayAnimation(1);
        //animatedSprite2.SetRenderer(animator2);
        animatedSprite.SetRenderer(animator);




        level.AddObject(oval);
        level.AddObject(rect);
        level.AddObject(sprite);
        level.AddObject(animatedSprite);
        //level.AddObject(animatedSprite2);



        return level;
    }

    public J_Engine GetEngine()
    {
        return engine;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
