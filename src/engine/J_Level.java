package engine;

import engine.game_objects.J_GameObject;
import javafx.collections.ObservableList;


import java.io.*;
import java.util.LinkedList;

public class J_Level implements Externalizable
{
    String name = "";
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

    public void SaveLevel(File level) throws IOException
    {
        FileOutputStream saveFile = new FileOutputStream(level);
        ObjectOutputStream outStream = new ObjectOutputStream(saveFile);
        this.writeExternal(outStream);
        outStream.flush();
        saveFile.close();
    }
    public void LoadLevel(File level) throws IOException, ClassNotFoundException
    {
        FileInputStream loadFile = new FileInputStream(level);
        ObjectInputStream inStream = new ObjectInputStream(loadFile);
        this.readExternal(inStream);
        inStream.close();
        loadFile.close();
    }

    @Override
    public String toString()
    {
        String out = "[J_Level(" + name + ") objects={";
        for(J_GameObject obj : objects)
        {
            out.concat("\n\t" + obj.toString());
        }
        return out + "}]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeUTF(name);
        out.writeObject(objects);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        name = in.readUTF();
        objects = (LinkedList<J_GameObject>)in.readObject();
    }

}
