/*
This class contains the window and main game loop
as well as calls to the updates of all other systems
of the engine.
*/
package engine;

import engine.framework.Vector2;
import engine.framework.GameObject;
import engine.framework.Sprite;
import engine.framework.basic_shapes.Oval;
import engine.framework.basic_shapes.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;


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

    Oval[] ovals;
    Rectangle[] rectangles ;
    Sprite[] sprites;

    LinkedList<GameObject> objects = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle(title);
        renderer = new Renderer(width, height);

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        InitializeGameObjects();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(framerate),
                (event) ->{
                    timeFirst = System.nanoTime();
                    deltaTime = timeFirst - timeLast;
                    timeLast = timeFirst;



                    //update everything here
                    renderer.clear();
                    renderer.draw(objects);
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

    public void InitializeGameObjects()
    {
        rectangles = new Rectangle[1];
        ovals = new Oval[1];
        sprites = new Sprite[1];

        Rectangle rect = new Rectangle(new Vector2(50, 50), new Vector2(150, 250), Color.GOLDENROD);
        rectangles[0] = rect;

        Oval circle = new Oval(new Vector2(500, 50), new Vector2(70,70), Color.web("#ffaa00"));
        ovals[0] = circle;

        Sprite sprite = new Sprite(new Vector2(400, 200), new Vector2(320, 200), new Image("engine/images/PhandroidRobotPlain.PNG"));

        sprites[0] = sprite;

        objects.add(rect);
        objects.add(circle);
        objects.add(sprite);
    }
}