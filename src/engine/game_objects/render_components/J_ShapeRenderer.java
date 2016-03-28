package engine.game_objects.render_components;

import engine.game_objects.J_Transform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class J_ShapeRenderer extends J_RendererComponent
{
    Color stroke;
    Color fill;

    public J_ShapeRenderer()
    {
        stroke = Color.ANTIQUEWHITE;
        fill = Color.HOTPINK;
    }

    public J_ShapeRenderer(J_Transform transform, Color stroke, Color fill)
    {
        super(transform);
        this.stroke = stroke;
        this.fill = fill;
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(fill);
        gc.setStroke(stroke);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeDouble(stroke.getRed());
        out.writeDouble(stroke.getGreen());
        out.writeDouble(stroke.getBlue());
        out.writeDouble(stroke.getOpacity());

        out.writeDouble(fill.getRed());
        out.writeDouble(fill.getGreen());
        out.writeDouble(fill.getBlue());
        out.writeDouble(fill.getOpacity());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {

        double sr = in.readDouble();
        double sg = in.readDouble();
        double sb = in.readDouble();
        double so = in.readDouble();

        double fr = in.readDouble();
        double fg = in.readDouble();
        double fb = in.readDouble();
        double fo = in.readDouble();

        this.stroke = new Color(sr, sg, sb, so);
        this.fill = new Color(fr, fg, fb, fo);
    }
}
