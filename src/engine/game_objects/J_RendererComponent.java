package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;

public abstract class J_RendererComponent extends J_Component implements J_Drawable
{
    protected J_Transform transform;

    public J_RendererComponent(J_Transform transform)
    {
        this.transform = transform;
    }
}
