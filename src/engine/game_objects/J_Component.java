package engine.game_objects;

public abstract class J_Component implements J_Updatable
{
    public void AddToGameObject(J_GameObject obj)
    {
        obj.AddComponent(this);
    }
}
