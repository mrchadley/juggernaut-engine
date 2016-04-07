package engine.game_objects;

import engine.framework.J_Updatable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

import java.io.Externalizable;


public abstract class J_Component implements J_Updatable, Externalizable
{
    public void AddToGameObject(J_GameObject obj)
    {
        obj.AddComponent(this);
    }
    public abstract TitledPane GetProperties();
    public abstract void SetTransform(J_Transform newTransform);
}
