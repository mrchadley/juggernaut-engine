package juggernaut_engine;

import juggernaut_engine.framework.GameObject;
import juggernaut_engine.framework.Vector2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import juggernaut_engine.input.J_Input;

import java.util.LinkedList;

public abstract class J_Engine extends Application
{
    private String title = "[Juggernaut Engine]";
    private float frameRate;

    private Timeline gameLoop;

    private float currentTime;
    private float previousTime;
    private float deltaTime;
    private int skippedFrames = 1;

    private JuggernautRenderer renderer;
    private J_Input input = new J_Input();

    private JuggernautInitializer jInit = new JuggernautInitializer();
    LinkedList<GameObject> objects = jInit.getObjects();

    private final float MAX_TIME_DIFFERENCE = 0.5f;
    private final int MAX_SKIPPED_FRAMES = 5;

    public void Startup(String title, int width, int height, int framesPerSecond)
    {
        this.title = title + " " + this.title;
        renderer = new JuggernautRenderer(width, height);
        this.frameRate = 1 / (float)framesPerSecond;

        renderer.getScene().addEventHandler(KeyEvent.ANY, input.GetKeyboard());
        renderer.getScene().addEventHandler(MouseEvent.ANY, input.GetMouse());
        renderer.getScene().addEventHandler(ScrollEvent.ANY, input.GetScrollWheel());
    }

    public void Run(Stage primaryStage)
    {
        primaryStage.setTitle(title);

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        previousTime = System.nanoTime() / 1000000000.0f;
        KeyFrame tick = new KeyFrame(
                Duration.seconds(frameRate),
                (event)->{
                    currentTime = System.nanoTime() / 1000000000.0f;
                    deltaTime = currentTime - previousTime;


                    // !!!~~ADD THAT LOOP THAT MAKES IT MORE CONSISTENT--!!!

                    /*
                    do all the update stuff
                    -> process input
                    -> update logic
                    -> render(clear screen -> draw)
                     */

                    Update();
                    Draw();

                    previousTime = currentTime;
                });
        gameLoop.getKeyFrames().add(tick);
        gameLoop.play();

        primaryStage.setScene(renderer.getScene());
        primaryStage.show();
    }


    public void Stop()
    {
        gameLoop.stop();
        Shutdown();
    }

    public abstract void Shutdown();
    public void Update()
    {
        //input.Update();
        if(input.GetKeyDown(KeyCode.A))
        {
            J_Logger.debug("test", "pressing a");
            objects.get(2).setPosition(objects.get(2).getPosition().add(new Vector2(-5,0)));
        }
        if(input.GetKeyDown(KeyCode.D))
        {
            J_Logger.debug("test", "pressing a");
            objects.get(2).setPosition(objects.get(2).getPosition().add(new Vector2(5,0)));
        }
    }
    private void Draw()
    {
        renderer.clear();
        renderer.draw(objects);
    }
}
