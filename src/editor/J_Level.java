package editor;

import engine.framework.GameObject;

import java.util.LinkedList;

public class J_Level
{
    private LinkedList<GameObject> objects = new LinkedList<>();

    public void AddObject(GameObject obj)
    {
        objects.add(obj);
    }
    public LinkedList<GameObject> GetObjects()
    {
        return objects;
    }
}
