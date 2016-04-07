package engine.game_objects;

import engine.J_Log;
import engine.framework.Vector2;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

public class J_Transform extends J_Component {
    private Vector2 position; //the top-left corner of the object(global) >> used for the renderer
    private Vector2 localPosition; //center of the object in relation to the parent's center
    private Vector2 positionCenter; //the center of the game object(global)
    private Vector2 size;

    private J_Transform parent = null;
    private LinkedList<J_Transform> children = new LinkedList<>();

    public J_Transform()
    {
        position = new Vector2();
        localPosition = new Vector2();
        positionCenter = new Vector2();
        size = new Vector2();
    }
    public J_Transform(Vector2 pos, Vector2 size)
    {
        J_Log.debug("j_transform", "initializing");
        localPosition = pos;
        positionCenter = pos;
        this.size = size;

        position = positionCenter.add(size.multiply(-0.5f));
    }
    public Vector2 GetCorner()
    {
        return position;
    }
    public Vector2 GetPosition()
    {
        return positionCenter;
    }
    public Vector2 GetSize()
    {
        return size;
    }

    public Vector2 GetLocalPosition()
    {
        return localPosition;
    }
    public J_Transform GetParent()
    {
        return parent;
    }
    public LinkedList<J_Transform> GetChildren()
    {
        return children;
    }

    @Override
    public void Update(float dt)
    {
        if(parent != null) //check to see if the object has a parent and adjust local position accordingly
        {
            positionCenter = parent.positionCenter.add(localPosition);
        }
        else
        {
            positionCenter = localPosition;
        }
        position = positionCenter.add(size.multiply(-0.5f));
    }
    public void Move(Vector2 amount) //moves object in relation to parent
    {
        localPosition = localPosition.add(amount);
    }
    public void SetParent(J_Transform parent)
    {
        this.localPosition = ComputeLocalPos(parent, this);
        parent.AddChild(this);
    }
    public void AddChild(J_Transform child)
    {
        child.localPosition = ComputeLocalPos(this, child);
        child.parent = this;
        children.add(child);
    }
    public void RemoveParent()
    {
        J_Log.debug(J_Transform.this.toString(), "removing parent");
        parent = null;
        localPosition = ComputeLocalPos(parent, this);

    }
    public void RemoveChild(J_Transform child)
    {
        if(children.contains(child))
        {
            child.RemoveParent();
            children.remove(child);
        }
    }
    private static Vector2 ComputeLocalPos(J_Transform parent, J_Transform child)
    {
        if(parent == null)
            return parent.positionCenter.subtract(child.positionCenter);
        else
            return child.positionCenter;
    }

    @Override
    public TitledPane GetProperties()
    {
        TitledPane transformPane = new TitledPane();
        transformPane.setText("Transform");

        GridPane transformContent = new GridPane();
        transformContent.setPadding(new Insets(10,15,10,15));
        transformContent.setHgap(5);
        transformContent.setVgap(5);

        TextField xPosField = new TextField(localPosition.getX() + "");
        xPosField.setPrefWidth(75);
        xPosField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                System.out.println("text: " + xPosField.getText());
                System.out.println("float: " + Float.parseFloat(xPosField.getText()));

                localPosition.setX(Float.parseFloat(xPosField.getText()));
                System.out.println("localPosX: " + localPosition.getX());
                System.out.println("centerPosX: " + positionCenter.getX());
                System.out.println("posX: " + position.getX());
            }
        });

        TextField yPosField = new TextField(localPosition.getY() + "");
        yPosField.setPrefWidth(75);
        yPosField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                localPosition.setY(Float.parseFloat(yPosField.getText()));
            }
        });

        TextField xSizeField = new TextField(size.getX() + "");
        xSizeField.setPrefWidth(75);
        xSizeField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                size.setX(Float.parseFloat(xSizeField.getText()));
            }
        });

        TextField ySizeField = new TextField(size.getY() + "");
        ySizeField.setPrefWidth(75);
        ySizeField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER)
            {
                size.setY(Float.parseFloat(ySizeField.getText()));
            }
        });

        Label positionLabel = new Label("Position:");
        transformContent.add(positionLabel, 0, 0);
        transformContent.add(new Label("x:"), 1, 0);
        transformContent.add(xPosField, 2, 0);
        transformContent.add(new Label("y:"), 3, 0);
        transformContent.add(yPosField, 4, 0);

        Label sizeLabel = new Label("Size:");
        transformContent.add(sizeLabel, 0, 1);
        transformContent.add(new Label("x:"), 1, 1);
        transformContent.add(xSizeField, 2, 1);
        transformContent.add(new Label("y:"), 3, 1);
        transformContent.add(ySizeField, 4, 1);

        transformPane.setContent(transformContent);

        return transformPane;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(position);
        out.writeObject(localPosition);
        out.writeObject(positionCenter);
        out.writeObject(size);

        out.writeObject(parent);
        out.writeObject(children);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.position = (Vector2)in.readObject();
        this.localPosition = (Vector2)in.readObject();
        this.positionCenter = (Vector2)in.readObject();
        this.size = (Vector2)in.readObject();

        this.parent = (J_Transform)in.readObject();
        this.children = (LinkedList<J_Transform>)in.readObject();
    }

    @Override
    public String toString()
    {
        String output =  "[Transform: position=" + position.toString() + " localPosition=" + localPosition.toString()
                + " positionCenter=" + positionCenter.toString() + " size=" + size.toString() + " parent=";

        if(parent == null)
            output += "none";
        else
            output += (parent.toString());

        output += (" children=");
        if(children.isEmpty())
            output += ("none");
        else
            for(J_Transform child : children)
                output += (" \n~" + child.toString());
        return output + "]";
    }

    @Override
    public void SetTransform(J_Transform newTransform) {

    }
}
