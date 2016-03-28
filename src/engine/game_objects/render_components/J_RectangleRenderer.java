package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class J_RectangleRenderer extends J_ShapeRenderer
{
    public J_RectangleRenderer()
    {

    }
    public J_RectangleRenderer(J_Transform transform, Color stroke, Color fill)
    {
        super(transform, stroke, fill);
    }
    @Override
    public void Draw(GraphicsContext gc)
    {
        super.Draw(gc);
        gc.fillRect(transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
        gc.strokeRect(transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
    }

    @Override
    public void Update(float dt)
    {

    }

    @Override
    public void DisplayProperties()
    {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }

    @Override
    public String toString()
    {
        return "[J_RectangleRenderer: transform=" + transform.toString() + " fill=" + fill.toString() + " stroke=" + stroke.toString() + "]";
    }
}
