package engine.game_objects;

import engine.framework.J_Updatable;

import java.io.Externalizable;


public abstract class J_Component implements J_Updatable, Externalizable
{
    public void AddToGameObject(J_GameObject obj)
    {
        obj.AddComponent(this);
    }
}
