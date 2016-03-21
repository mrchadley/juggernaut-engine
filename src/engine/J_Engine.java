package engine;

import editor.J_Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import juggernaut_engine.J_Log;


//code that links together the framework of the engine such as the renderer, the input handler, and anything else that may be added
public class J_Engine
{
    private static J_Engine instance = new J_Engine();

    private J_Time time = J_Time.Time();
    private float currentTime;
    private float previousTime;

    private J_Renderer renderer;
    private J_InputHandler inputHandler = J_InputHandler.GetInstance();
    private Timeline gameloop;
    private KeyFrame tick;

    private J_Level currentLevel;

    private final String TITLE = " - [JUGGERNAUT_ENGINE]";

    //private constructor so that only one instance of the J_Engine class can be created
    private J_Engine()
    {
        J_Log.debug("j_engine", "creating");
        currentLevel = new J_Level();
    }

    //initialize everything in here because the constructor was made to be private
    public void Initialize(String name, int width, int height, int fps, Stage primaryStage)
    {
        J_Log.debug("j_engine", "initializing");
        primaryStage.setTitle(name + TITLE);
        renderer = new J_Renderer(width, height);
        renderer.GetScene().addEventHandler(InputEvent.ANY, inputHandler);

        gameloop = new Timeline();
        gameloop.setCycleCount(Timeline.INDEFINITE);
        tick = new KeyFrame(Duration.seconds(1/(double)fps), event ->
        {
            currentTime = System.nanoTime() / 1000000000.0f;
            time.SetDeltaTime(currentTime - previousTime);
            //the actual code that loops goes here
            //update, draw, handle input, etc/
            Update(time.GetDeltaTime());
            renderer.Draw(currentLevel);

            previousTime = currentTime;
            //J_Log.debug("j_engine", "looping, deltaTime = " + time.GetDeltaTime() + ", elapsedTime = " + time.GetElapsedTime());
        });
        gameloop.getKeyFrames().add(tick);

        primaryStage.setScene(renderer.GetScene());
        primaryStage.show();
    }

    public void Run()
    {
        J_Log.debug("j_engine", "running");
        previousTime = System.nanoTime() / 1000000000.0f;
        gameloop.play();
    }
    public void Stop()
    {
        J_Log.debug("j_engine", "stopping");
        gameloop.stop();
    }

    public void Update(float dt)
    {
        currentLevel.Update(dt);
    }

    public void SetLevel(J_Level level)
    {
        currentLevel = level;
    }
    public J_Level GetCurrentLevel()
    {
        return currentLevel;
    }

    public static J_Engine GetInstance()
    {
        return instance;
    }
}

class J_Time
{
    private static J_Time instance = new J_Time();
    private float elapsedTime = 0.0f;
    private float deltaTime = 0.0f;

    private J_Time()
    { }

    public float GetDeltaTime()
    {
        return deltaTime;
    }

    public void SetDeltaTime(float dt)
    {
        deltaTime = dt;
        elapsedTime += deltaTime;
    }

    public float GetElapsedTime()
    {
        return elapsedTime;
    }

    public static J_Time Time()
    {
        return instance;
    }
}