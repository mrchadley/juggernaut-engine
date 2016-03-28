package engine.game_objects.render_components;

import engine.framework.Vector2;
import engine.game_objects.J_Transform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class J_SpriteRenderer extends J_RendererComponent
{
    private J_SpriteAnimation sprite;

    public J_SpriteRenderer()
    {

    }

    public J_SpriteRenderer(J_Transform transform, Image sprite)
    {
        super(transform);
        this.sprite = new J_SpriteAnimation(sprite, new Vector2((float)sprite.getWidth(), (float)sprite.getHeight()), 1, 0);
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        gc.drawImage(sprite.GetSpriteSheet(), transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void DisplayProperties()
    {

    }

    public void SetSprite(J_SpriteAnimation sprite)
    {
        this.sprite = sprite;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(sprite);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.sprite = (J_SpriteAnimation)in.readObject();
    }
}
