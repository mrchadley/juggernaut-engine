package engine.framework.basic_shapes;

import engine.framework.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {

    public Rectangle(Vector2 position, Vector2 size, Color color) {
        super(position, size, color);
    }


    public void update() {

    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}