package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class J_ShapeRenderer extends J_RendererComponent
{
    Color stroke;
    Color fill;

    public J_ShapeRenderer(J_Transform transform, Color stroke, Color fill)
    {
        super(transform);
        this.stroke = stroke;
        this.fill = fill;
    }

    @Override
    public void Draw(GraphicsContext gc)
    {
        gc.setFill(fill);
        gc.setStroke(stroke);
    }
}
