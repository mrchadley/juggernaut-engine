package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class J_OvalRenderer extends J_ShapeRenderer
{
    public J_OvalRenderer()
    {
        super();
    }
    public J_OvalRenderer(J_Transform transform, Color stroke, Color fill)
    {
        super(transform, stroke, fill);
    }
    @Override
    public void Draw(GraphicsContext gc)
    {
        super.Draw(gc);
        gc.fillOval(transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
        gc.strokeOval(transform.GetCorner().getX(), transform.GetCorner().getY(), transform.GetSize().getX(), transform.GetSize().getY());
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
        return "[J_OvalRenderer: transform=" + transform.toString() + " fill=" + fill.toString() + " stroke=" + stroke.toString() + "]";
    }

    @Override
    public TitledPane GetProperties()
    {
        TitledPane ovalRendererPane = new TitledPane();
        ovalRendererPane.setText("Oval Renderer");

        GridPane ovalRendererContent = new GridPane();
        ovalRendererContent.setPadding(new Insets(10,15,10,15));
        ovalRendererContent.setHgap(5);
        ovalRendererContent.setVgap(5);

        ColorPicker strokePicker = new ColorPicker(stroke);
        ColorPicker fillPicker = new ColorPicker(fill);

        ovalRendererContent.add(new Label("Stroke:"), 0, 0);
        ovalRendererContent.add(strokePicker, 1, 0);

        ovalRendererContent.add(new Label("Fill:"), 0, 1);
        ovalRendererContent.add(fillPicker, 1, 1);

        ovalRendererPane.setContent(ovalRendererContent);

        return ovalRendererPane;
    }
}
