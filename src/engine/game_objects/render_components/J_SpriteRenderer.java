package engine.game_objects.render_components;

import engine.framework.Vector2;
import engine.game_objects.J_Transform;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.*;

public class J_SpriteRenderer extends J_RendererComponent
{
    private J_SpriteAnimation sprite;

    public J_SpriteRenderer()
    {

    }
    public J_SpriteRenderer(J_Transform transform, Image img)
    {
        super(transform);
        sprite = new J_SpriteAnimation(img, new Vector2((float)img.getWidth(), (float)img.getHeight()), 1, 0);
    }
    public J_SpriteRenderer(J_Transform transform)
    {
        super(transform);
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        if(sprite != null) {
            gc.drawImage(sprite.GetSpriteSheet(), transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
        }
    }

    @Override
    public void Update(float dt) {

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

    @Override
    public TitledPane GetProperties()
    {
        TitledPane spriteRendererPane = new TitledPane();
        spriteRendererPane.setText("Sprite Renderer");

        spriteRendererPane.setContent(sprite.GetProperties());

        return spriteRendererPane;
    }
}
