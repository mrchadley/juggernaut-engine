package editor;

import juggernaut_engine.framework.GameObject;

import java.util.LinkedList;

public class J_Level
{
    private LinkedList<GameObject> objects = new LinkedList<>();

    public void Update()
    {

    }

    public void AddObject(GameObject obj)
    {
        objects.add(obj);
    }
    public LinkedList<GameObject> GetObjects()
    {
        return objects;
    }
}
