package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class J_SpriteRenderer extends J_RendererComponent
{
    private J_SpriteAnimation sprite;
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
}
