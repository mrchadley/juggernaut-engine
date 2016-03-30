package engine.game_objects.render_components;

import engine.framework.J_Drawable;
import engine.game_objects.J_Component;
import engine.game_objects.J_Transform;

public abstract class J_RendererComponent extends J_Component implements J_Drawable
{

    protected J_Transform transform;

    public J_RendererComponent()
    {

    }

    public J_RendererComponent(J_Transform transform)
    {
        this.transform = transform;
    }

    public void SetTransform(J_Transform newTransform)
    {
        transform = newTransform;
    }
}
