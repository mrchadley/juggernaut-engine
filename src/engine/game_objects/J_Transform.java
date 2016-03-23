package engine.game_objects;

import juggernaut_engine.J_Log;
import juggernaut_engine.framework.Vector2;

import java.util.LinkedList;

public class J_Transform extends J_Component {
    private Vector2 position; //the top-left corner of the object(global) >> used for the renderer
    private Vector2 localPosition; //center of the object in relation to the parent's center
    private Vector2 positionCenter; //the center of the game object(global)
    private Vector2 size;

    private J_Transform parent = null;
    private LinkedList<J_Transform> children = new LinkedList<>();

    public J_Transform(Vector2 pos, Vector2 size)
    {
        localPosition = pos;
        positionCenter = pos;
        this.size = size;

        position = positionCenter.add(size.multiply(-0.5f));
    }

    protected Vector2 GetCorner()
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

    public void DisplayProperties()
    {

    }
}
