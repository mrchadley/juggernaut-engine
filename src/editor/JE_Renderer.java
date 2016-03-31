package editor;

import engine.J_Level;
import engine.game_objects.J_GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import engine.J_Log;


public class JE_Renderer
{
    private J_Level level;
    private GraphicsContext gc;
    private Canvas canvas;

    private Timeline editorLoop = new Timeline();
    private KeyFrame tick;

    private static JE_Renderer instance = new JE_Renderer();

    private boolean running = false;

    private JE_Renderer()
    {
        //J_Log.debug("sceneview", "initializing");
        editorLoop.setCycleCount(Timeline.INDEFINITE);
        tick = new KeyFrame(Duration.millis(34), event ->
        {
            Update();
            Draw();
            //J_Log.debug("sceneview", "looping");
        });
        editorLoop.getKeyFrames().add(tick);
    }
    public void SetLevel(J_Level level)
    {
        this.level = level;
    }
    public void SetCanvas(Canvas canvas)
    {
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
    }
    public void Run()
    {
        editorLoop.play();
    }


    public void Draw()
    {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.rgb(60, 60, 190));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for(J_GameObject obj : level.GetObjects())
        {
            obj.Draw(gc);
        }
    }
    public void Update()
    {
        if(running)
        {
            level.Update((float)1/30);
        }
    }

    public void SetRunning(boolean bool)
    {
        running = bool;
    }
    public boolean isRunning()
    {
        return running;
    }

    public void Stop()
    {
        editorLoop.stop();
    }
    public static JE_Renderer GetInstance()
    {
        return instance;
    }
    public Canvas GetCanvas()
    {
        return canvas;
    }
}
