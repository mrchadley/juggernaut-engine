package juggernaut_engine.framework;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject
{
    protected Vector2 position;
    protected Vector2 velocity = Vector2.zero();
    protected Vector2 size;
    protected Vector2 center;

    public GameObject(Vector2 position, Vector2 size)
    {
        this.setPosition(position);
        this.setSize(size);
        this.center = this.size.multiply(0.5f);
    }

    public abstract void update();
    public abstract void draw(GraphicsContext gc);

    public abstract Vector2 getPosition();
    public abstract Vector2 getVelocity();
    public abstract Vector2 getSize();

    public abstract void setPosition(Vector2 position);
    public abstract void setVelocity(Vector2 velocity);
    public abstract void setSize(Vector2 size);

}
