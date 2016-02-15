package engine.framework;



public abstract class GameObject
{
    protected Vector2 position;
    protected Vector2 velocity = Vector2.zero();

    protected ObjectId id;

    public GameObject(float x, float y, ObjectId id)
    {
        position.setX(x);
        position.setY(y);
        this.id = id;
    }

    public abstract void update();
    public abstract void draw();

    public abstract Vector2 getPosition();
    public abstract Vector2 getVelocity();
    public abstract ObjectId getId();

    public abstract void setPosition(Vector2 position);
    public abstract void setPosition(float x, float y);
    public abstract void setVelocity(Vector2 velocity);
    public abstract void setVelocity(float x, float y);
}
