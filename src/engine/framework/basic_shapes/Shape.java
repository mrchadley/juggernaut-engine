package engine.framework.basic_shapes;

import engine.framework.GameObject;
import engine.framework.Vector2;
import javafx.scene.paint.Color;

public abstract class Shape extends GameObject
{

    protected Color color;

    public Shape(engine.framework.Vector2 position, Vector2 size, Color color)
    {
        super(position, size);
        this.color = color;
    }
    public abstract void setColor(Color color);
}
