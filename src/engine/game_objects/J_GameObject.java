package engine.game_objects;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class J_GameObject implements J_Updatable, J_Drawable
{
    private String name = "GameObject";
    private J_Transform transform = new J_Transform(Vector2.zero(), Vector2.one());
    private LinkedList<J_Component> components = new LinkedList<>();

    public J_GameObject(String name, J_Transform transform)
    {
        this.name = name;
        if(transform != null)
        {
            this.transform = transform;
        }
    }

    @Override
    public void Update(float dt)
    {
        transform.Update(dt);

        for(J_Component comp : components)
        {
            comp.Update(dt);
        }
    }
    @Override
    public void Draw(GraphicsContext gc)
    {

        for(J_Component comp : components)
        {
            if(J_RendererComponent.class.isAssignableFrom(comp.getClass()))
            {
                J_RendererComponent temp = (J_RendererComponent)comp;
                temp.Draw(gc);
            }
        }
    }

    @Override
    public void DisplayProperties() {

    }

    public J_Transform GetTransform()
    {
        return transform;
    }

    public J_Component GetComponent(int index)
    {
        if(index < components.size())
        {
            return components.get(index);
        }
        return null;
    }

    public LinkedList<J_Component> GetComponents()
    {
        return components;
    }

    public void AddComponent(J_Component component)
    {
        components.add(component);
    }
}
