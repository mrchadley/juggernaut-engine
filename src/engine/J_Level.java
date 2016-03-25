package engine;

import engine.game_objects.J_GameObject;


import java.util.LinkedList;

public class J_Level
{
    private LinkedList<J_GameObject> objects = new LinkedList<>();


    public void Update(float dt)
    {
        for(J_GameObject obj : objects)
        {
            obj.Update(dt);
        }
    }

    public void AddObject(J_GameObject obj)
    {
        objects.add(obj);
    }

    public LinkedList<J_GameObject> GetObjects()
    {
        return objects;
    }
}
