package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;

public class J_ShapeRenderer extends J_RendererComponent
{
    public enum Shape
    {
        RECTANGLE("Rectangle", 0),
        OVAL("Oval", 1);

        private String name;
        private int shape;

        private Shape(String name, int shape)
        {
            this.name = name;
            this.shape = shape;
        }
    }
    public J_ShapeRenderer(J_Transform transform)
    {
        super(transform);
    }
    @Override
    public void Draw(GraphicsContext gc) {

    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void DisplayProperties() {

    }
}
