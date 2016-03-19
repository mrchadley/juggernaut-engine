package juggernaut_engine.framework.basic_shapes;

import juggernaut_engine.framework.GameObject;
import juggernaut_engine.framework.Vector2;
import javafx.scene.paint.Color;

public abstract class Shape extends GameObject
{

    protected Color color;

    public Shape(juggernaut_engine.framework.Vector2 position, Vector2 size, Color color)
    {
        super(position, size);
        this.color = color;
    }
    public abstract void setColor(Color color);
}
