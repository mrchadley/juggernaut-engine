/*
This class contains the window and main game loop
as well as calls to the updates of all other systems
of the engine.
*/
package engine;

import engine.framework.GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//figure out a way to pass screen width and height as well as the title and framerate as command line arguments
public class JuggernautEngine extends Application
{
    private float framerate = 0.017f;
    private int width = 800, height = 600;
    private String title = "Juggernaut Engine";

    private Renderer renderer;

    private Timeline gameLoop;
    private float deltaTime;

    private float timeFirst;
    private float timeLast;

    private List<GameObject> gameObjects;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle(title);
        renderer = new Renderer(width, height);

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(
                Duration.seconds(framerate),
                (event) ->{
                    timeFirst = System.nanoTime() / 1000000000.0f;
                    deltaTime = timeFirst - timeLast;
                    timeLast = timeFirst;


                    //update everything here
                    renderer.clear();
                    renderer.draw();
                });
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        primaryStage.setScene(renderer.getScene());
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
