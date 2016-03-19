package juggernaut_engine.framework;

public class Vector2
{
    private float x;
    private float y;

    public Vector2(float x, float y)
    {
        this.setX(x);
        this.setY(y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public  Vector2 add(Vector2 other)
    {
        return new Vector2(x + other.getX(), y + other.getY());
    }

    public  Vector2 multiply(float other)
    {
        return new Vector2(x * other, y * other);
    }

    public static Vector2 zero()
    {
        return new Vector2(0,0);
    }
    public static Vector2 x() { return new Vector2(1,0); }
}
