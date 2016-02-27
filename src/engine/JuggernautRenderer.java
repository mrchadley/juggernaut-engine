package engine;

import engine.framework.GameObject;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class JuggernautRenderer
{
    @FXML
    private Canvas canvas;

    private Scene scene;
    private Group root;
    private GraphicsContext gc;

    //test
    int testInt = 0;

    //private list of drawObjects

    public JuggernautRenderer(int width, int height)
    {
        canvas = new Canvas();
        canvas.widthProperty().setValue(width);
        canvas.heightProperty().setValue(height);

        root = new Group();
        scene = new Scene(root, width, height);
        root.getChildren().add(canvas);


        gc = canvas.getGraphicsContext2D();
    }

    public void draw(LinkedList<GameObject> objects) //iterate through list of gameObjects and call their draw functions
    {
        for(GameObject obj : objects)
        {
            obj.draw(gc);
        }
    }

    public void clear()
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.web("#232329"));
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public Scene getScene() {
        return scene;
    }

    public GraphicsContext getGc()
    {
        return gc;
    }

}
