package engine.game_objects;

/**
 * Created by 100555250 on 3/21/2016.
 */
public abstract class J_Component
{
    public abstract void Update(float dt);

    public abstract void DisplayProperties();

    public void AddToGameObject(J_GameObject obj)
    {
        obj.AddComponent(this);
    }
}
