package engine.game_objects;

import java.util.LinkedList;

public class J_GameObject
{
    private String name = "GameObject";
    private J_Transform transform;
    private LinkedList<J_Component> components = new LinkedList<>();

    public J_GameObject()
    {
        //renderer = new J_ShapeRenderer()
    }

    public void AddComponent(J_Component component) {
    }
}
