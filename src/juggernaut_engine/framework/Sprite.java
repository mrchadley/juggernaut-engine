package juggernaut_engine.framework;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite extends GameObject
{
    private Image image;
    public Sprite(Vector2 position, Vector2 size, Image image)
    {
        super(position, size);
        this.image = image;
    }

    public void update() {

    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(image, position.getX(), position.getY(), size.getX(), size.getY());
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
}
