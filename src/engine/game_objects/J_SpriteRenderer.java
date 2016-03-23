package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by marct on 2016-03-23.
 */
public class J_SpriteRenderer extends J_RendererComponent
{
    Image sprite;
    J_SpriteRenderer(J_Transform transform, Image sprite)
    {
        super(transform);
        this.sprite = sprite;
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        gc.drawImage(sprite,0,0); ///<-FIX THIS LINE
    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void DisplayProperties() {

    }
}
