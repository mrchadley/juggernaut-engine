package engine;

import editor.J_Level;
import engine.game_objects.J_OvalRenderer;
import engine.game_objects.J_ShapeRenderer;
import engine.game_objects.J_Transform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import juggernaut_engine.J_Log;
import juggernaut_engine.framework.GameObject;
import juggernaut_engine.framework.Vector2;

public class J_Renderer{

    private Group root;
    private Scene scene;

    private Canvas canvas;
    private GraphicsContext gc;

    public J_Renderer(int width, int height)
    {
        J_Log.debug("j_renderer", "initializing");

        canvas = new Canvas(width, height);
        root = new Group();
        scene = new Scene(root, width, height);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    public void Draw(J_Level level)
    {
        Clear();
        for(GameObject obj : level.GetObjects())
        {
            obj.draw(gc);
        }
    }

    public void Clear()
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.rgb(100, 100, 150));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public Scene GetScene()
    {
        return scene;
    }

    public GraphicsContext GetGC()
    {
        return gc;
    }
}
