package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
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

    @Override
    public TitledPane GetProperties()
    {
        TitledPane rectRendererPane = new TitledPane();
        rectRendererPane.setText("Rectangle Renderer");

        GridPane rectRendererContent = new GridPane();
        rectRendererContent.setPadding(new Insets(10,15,10,15));
        rectRendererContent.setHgap(5);
        rectRendererContent.setVgap(5);

        ColorPicker strokePicker = new ColorPicker(stroke);
        ColorPicker fillPicker = new ColorPicker(fill);

        rectRendererContent.add(new Label("Stroke:"), 0, 0);
        rectRendererContent.add(strokePicker, 1, 0);

        rectRendererContent.add(new Label("Fill:"), 0, 1);
        rectRendererContent.add(fillPicker, 1, 1);

        rectRendererPane.setContent(rectRendererContent);

        return rectRendererPane;
    }
}
