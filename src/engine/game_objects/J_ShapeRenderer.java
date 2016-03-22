package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;

public class J_ShapeRenderer extends J_RendererComponent
{
    public enum Shape
    {
        RECTANGLE(0),
        OVAL(1);

        private int shape;

        private Shape(int shape)
        {
            this.shape = shape;
        }
    }
    public J_ShapeRenderer(J_Transform transform)
    {
        super(transform);
    }
    @Override
    public void Draw(GraphicsContext gc)
    {


    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void DisplayProperties() {

    }
}
