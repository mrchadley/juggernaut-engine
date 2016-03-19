package juggernaut_engine;

import juggernaut_engine.framework.GameObject;
import juggernaut_engine.framework.Sprite;
import juggernaut_engine.framework.Vector2;
import juggernaut_engine.framework.basic_shapes.Oval;
import juggernaut_engine.framework.basic_shapes.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class JuggernautInitializer
{
    private LinkedList<GameObject> objects = new LinkedList<>();

    public JuggernautInitializer()
    {
        //initialize all the game objects
        Rectangle rect = new Rectangle(new Vector2(50, 50), new Vector2(150, 250), Color.GOLDENROD);
        Oval circle = new Oval(new Vector2(500, 50), new Vector2(70,70), Color.web("#ffaa00"));
        Sprite sprite = new Sprite(new Vector2(400, 200), new Vector2(320, 200), new Image("juggernaut_engine/images/PhandroidRobotPlain.PNG"));

        objects.add(rect);
        objects.add(circle);
        objects.add(sprite);
    }

    public LinkedList<GameObject> getObjects()
    {
        return objects;
    }
}
