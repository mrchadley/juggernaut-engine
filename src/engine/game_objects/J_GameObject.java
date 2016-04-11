package engine.game_objects;

import editor.JE_Controller;
import engine.J_Log;
import engine.J_Renderer;
import engine.framework.J_Drawable;
import engine.framework.J_Updatable;
import engine.framework.Vector2;
import engine.game_objects.render_components.J_RendererComponent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.LinkedList;

public class J_GameObject implements J_Updatable, J_Drawable, Externalizable
{
    private String name = "GameObject";
    private J_Transform transform = new J_Transform(Vector2.zero(), Vector2.one());


    private J_RendererComponent renderer;
    private LinkedList<J_Component> components = new LinkedList<>();

    private J_GameObject parent;
    private LinkedList<J_GameObject> children = new LinkedList<>();

    public J_GameObject()
    {

    }

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
        if(renderer != null)
            renderer.Update(dt);

        for(J_Component comp : components)
        {
            comp.Update(dt);
        }
    }
    @Override
    public void Draw(GraphicsContext gc)
    {
        if(renderer != null)
            renderer.Draw(gc);
        /*
        for(J_Component comp : components)
        {
            if(J_RendererComponent.class.isAssignableFrom(comp.getClass()))
            {
                J_RendererComponent temp = (J_RendererComponent)comp;
                temp.Draw(gc);
            }
        }*/
    }

    public J_Transform GetTransform()
    {
        return transform;
    }

    public J_RendererComponent GetRenderer()
    {
        return renderer;
    }

    public J_Component GetComponent(int index)
    {
        if(index < components.size())
        {
            return components.get(index);
        }
        return null;
    }

    public void SetRenderer(J_RendererComponent renderer) {
        this.renderer = renderer;
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
        return name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeUTF(name);
        out.writeObject(transform);
        out.writeObject(renderer);
        out.writeObject(components);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        name = in.readUTF();
        transform = (J_Transform)in.readObject();
        renderer = (J_RendererComponent) in.readObject();
        components = (LinkedList<J_Component>) in.readObject();

        renderer.SetTransform(transform);
        for (J_Component comp: components)
        {
            comp.SetTransform(transform);
        }
        System.out.println(renderer.toString());
        System.out.println(transform.toString());
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetParent(J_GameObject parent)
    {
        parent.AddChild(this);
        transform.SetParent(parent.GetTransform());
    }
    public void AddChild(J_GameObject child)
    {
        child.parent = this;
        children.add(child);
        transform.AddChild(child.GetTransform());

    }
    public void RemoveParent()
    {
        parent = null;
        transform.RemoveParent();

    }
    public void RemoveChild(J_GameObject child)
    {
        if(children.contains(child))
        {
            child.RemoveParent();
            children.remove(child);
        }
        transform.RemoveChild(child.GetTransform());
    }

    public LinkedList<J_GameObject> GetChildren() {
        return children;
    }

    public J_GameObject GetParent() {
        return parent;
    }

    public VBox GetProperties(JE_Controller controller)
    {
        VBox properties = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField nameField = new TextField(name);
        nameField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                name = nameField.getText();
                controller.UpdateOutliner();
            }
        });

        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);

        properties.getChildren().add(gridPane);
        properties.getChildren().add(transform.GetProperties());
        if(renderer != null)
            properties.getChildren().add(renderer.GetProperties());

        for(J_Component comp : components)
        {
            properties.getChildren().add(comp.GetProperties());
        }
        return properties;
    }
}
