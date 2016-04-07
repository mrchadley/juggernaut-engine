package engine.game_objects;

import editor.JE_Renderer;
import engine.J_InputHandler;
import engine.framework.Vector2;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

public class J_InputBinder extends J_Component
{
    private J_Transform transform;
    private LinkedList<Pair<KeyCode, Vector2>> keyMap = new LinkedList<>();
    private J_InputHandler inputHandler;

    public J_InputBinder()
    {
        inputHandler = J_InputHandler.GetInstance();
    }
    @Override
    public void SetTransform(J_Transform newTransform)
    {
        transform = newTransform;
    }
    public void AddKey(KeyCode key, Vector2 velocity)
    {
        keyMap.add(new Pair<>(key, velocity));
    }

    @Override
    public void Update(float dt)
    {
        for(Pair<KeyCode, Vector2> pair : keyMap)
        {
            if(inputHandler.GetKeyDown(pair.getKey()))
            {
                transform.Move(pair.getValue().multiply(dt));
            }
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(keyMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        keyMap = (LinkedList<Pair<KeyCode,Vector2>>)in.readObject();
    }

    @Override
    public TitledPane GetProperties()
    {
        TitledPane inputBinderPane = new TitledPane();
        inputBinderPane.setText("Input Binder");
        inputBinderPane.setPrefWidth(280);

        GridPane inputBinderContent = new GridPane();
        inputBinderContent.setPadding(new Insets(10,15,10,15));
        inputBinderContent.setHgap(5);
        inputBinderContent.setVgap(5);

        Label bindings = new Label("Key Bindings:");
        GridPane.setColumnSpan(bindings, 4);
        inputBinderContent.add(bindings, 0, 0);

        ListView<KeyCode> list = new ListView<>();
        int i = 0;
        for(Pair<KeyCode, Vector2> pair : keyMap)
        {
            list.getItems().add(pair.getKey());
            i++;
        }
        list.setPrefWidth(250);
        GridPane.setColumnSpan(list, 7);
        inputBinderContent.add(list, 0, 1);

        ComboBox<KeyCode> key = new ComboBox<>();
        key.getItems().setAll(KeyCode.values());
        key.setPrefWidth(75);

        TextField xVel = new TextField();
        xVel.setPrefWidth(75);
        TextField yVel = new TextField();
        yVel.setPrefWidth(75);
        Button add = new Button("Add");
        add.setPrefWidth(75);

        inputBinderContent.add(new Label("Key:"), 0, 2);
        inputBinderContent.add(key, 1, 2);
        inputBinderContent.add(new Label("x:"), 2, 2);
        inputBinderContent.add(xVel, 3, 2);
        inputBinderContent.add(new Label("y:"), 4, 2);
        inputBinderContent.add(yVel, 5, 2);
        inputBinderContent.add(add, 6, 2);

        inputBinderPane.setContent(inputBinderContent);

        return inputBinderPane;
    }
}
