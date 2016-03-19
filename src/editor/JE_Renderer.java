package editor;

import engine.framework.GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import juggernaut_engine.J_Logger;

import java.util.LinkedList;

public class JE_Renderer
{
    private J_Level level;
    private GraphicsContext gc;
    private Canvas canvas;

    private Timeline editorLoop = new Timeline();
    private KeyFrame tick;

    private static JE_Renderer instance = new JE_Renderer();

    private JE_Renderer()
    {
        J_Logger.debug("sceneview", "initializing");
        editorLoop.setCycleCount(Timeline.INDEFINITE);
        tick = new KeyFrame(Duration.millis(180), event ->
        {
            Draw();
            J_Logger.debug("sceneview", "looping");
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

        for(GameObject obj : level.GetObjects())
        {
            obj.draw(gc);
        }
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
