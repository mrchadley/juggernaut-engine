package engine.game_objects;

import engine.framework.J_Drawable;
import engine.framework.J_Updatable;
import engine.framework.Vector2;
import engine.game_objects.render_components.J_RendererComponent;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.LinkedList;

public class J_GameObject implements J_Updatable, J_Drawable, Externalizable
{
    private String name = "GameObject";
    private J_Transform transform = new J_Transform(Vector2.zero(), Vector2.one());
    private LinkedList<J_Component> components = new LinkedList<>();

    public J_GameObject()
    {}

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

    public String toString()
    {
        String out =  "[J_GameObject(" + name + "): transform=" + transform.toString() + " components={";
        for(J_Component comp : components)
        {
            out.concat("\n\t" + components.toString());
        }
        return out + "}]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeUTF(name);
        out.writeObject(transform);
        out.writeObject(components);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        name = in.readUTF();
        transform = (J_Transform)in.readObject();
        components = (LinkedList<J_Component>) in.readObject();
    }
}
