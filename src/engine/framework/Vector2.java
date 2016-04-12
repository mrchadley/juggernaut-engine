package engine.framework;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Vector2 implements Externalizable
{
    private float x;
    private float y;

    public Vector2()
    {
        x = 0;
        y = 0;
    }

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

    public Vector2 add(Vector2 other)
    {
        return new Vector2(x + other.getX(), y + other.getY());
    }

    public Vector2 subtract(Vector2 other)
    {
        return new Vector2(x - other.getX(), y - other.getY());
    }

    public Vector2 multiply(float other)
    {
        return new Vector2(x * other, y * other);
    }

    public Vector2 divide(float other)
    {
        return new Vector2(x / other, y / other);
    }

    public Vector2 absolute()
    {
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    public static Vector2 zero()
    {
        return new Vector2(0,0);
    }

    public static Vector2 one()
    {
        return new Vector2(1, 1);
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeFloat(x);
        out.writeFloat(y);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.x = in.readFloat();
        this.y = in.readFloat();
    }
}
