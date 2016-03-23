package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class J_OvalRenderer extends J_ShapeRenderer
{
    public J_OvalRenderer(J_Transform transform, Color stroke, Color fill)
    {
        super(transform, stroke, fill);
    }
    @Override
    public void Draw(GraphicsContext gc)
    {
        if(fill != null)
            gc.fillOval(transform.GetCorner().getX(), transform.GetPosition().getY(), transform.GetSize().getX(), transform.GetSize().getY());
        if(stroke != null)
            gc.strokeOval(transform.GetCorner().getX(), transform.GetPosition().getY(), transform.GetSize().getX(), transform.GetSize().getY());
    }

    @Override
    public void Update(float dt)
    {

    }

    @Override
    public void DisplayProperties()
    {

    }
}
