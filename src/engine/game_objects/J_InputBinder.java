package engine.game_objects;

import editor.JE_Renderer;
import engine.J_InputHandler;
import engine.J_Log;
import engine.framework.Vector2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ObservableList<Pair<KeyCode, Vector2>> keyMap = FXCollections.observableArrayList();
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
        //J_Log.debug("input binder", "length: " + keyMap.size());
        for(Pair<KeyCode, Vector2> pair : keyMap)
        {
            if(inputHandler.GetKeyDown(pair.getKey()))
            {
                transform.Move(pair.getValue().multiply(dt));
                //J_Log.debug("input binder", "key pressed: " + pair.getKey() + ", " + pair.getValue());
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
        keyMap.clear();
        keyMap = (ObservableList<Pair<KeyCode,Vector2>>)in.readObject();
        inputHandler = J_InputHandler.GetInstance();
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

        TableView<Pair<KeyCode, Vector2>> table = new TableView();

        TableColumn keyCode = new TableColumn("KEY");
        keyCode.setCellValueFactory(new PropertyValueFactory<>("key"));
        keyCode.setPrefWidth(125);
        TableColumn vel = new TableColumn("VELOCITY");
        vel.setCellValueFactory(new PropertyValueFactory<>("value"));
        vel.setPrefWidth(125);

        table.setItems(keyMap);
        table.getColumns().addAll(keyCode, vel);

        table.setPrefWidth(250);
        GridPane.setColumnSpan(table, 7);
        inputBinderContent.add(table, 0, 1);

        ComboBox<KeyCode> key = new ComboBox<>();
        key.getItems().setAll(KeyCode.values());
        key.setPrefWidth(75);

        TextField xVel = new TextField();
        xVel.setPrefWidth(75);
        TextField yVel = new TextField();
        yVel.setPrefWidth(75);
        Button add = new Button("Add");
        add.setOnAction(event ->{
            KeyCode newKey = key.getSelectionModel().getSelectedItem();
            float xV = Float.parseFloat(xVel.getText());
            float yV = Float.parseFloat(yVel.getText());
            if(newKey != null)
            {
                AddKey(newKey, new Vector2(xV, yV));
                xVel.clear();
                yVel.clear();
            }
        });
        add.setPrefWidth(75);

        Label keyLabel = new Label("Key:");
        keyLabel.setPrefWidth(75);
        inputBinderContent.add(keyLabel, 0, 3);
        inputBinderContent.add(key, 1, 3);
        inputBinderContent.add(new Label("x:"), 0, 2);
        inputBinderContent.add(xVel, 1, 2);
        inputBinderContent.add(new Label("y:"), 2, 2);
        inputBinderContent.add(yVel, 3, 2);
        inputBinderContent.add(add, 3, 3);

        inputBinderPane.setContent(inputBinderContent);

        return inputBinderPane;
    }
}
