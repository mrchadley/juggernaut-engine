package juggernaut_engine.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class J_KeyboardHandler extends J_EventQueue<KeyEvent>
{
    protected static final int MAX_KEY_ORDINAL = 224;
    private boolean[] keys = new boolean[MAX_KEY_ORDINAL];
    private boolean[] keyQueues = new boolean[MAX_KEY_ORDINAL];

    @Override
    public void handle(KeyEvent event)
    {
        if(event.getEventType() == KeyEvent.KEY_PRESSED)//key being held
        {
            keys[event.getCode().ordinal()] = true;
        }
        if(event.getEventType() == KeyEvent.KEY_RELEASED)
        {
            keys[event.getCode().ordinal()] = false;
        }
    }

    @Override
    public void Update()
    {
        for(int i = 0; i < MAX_KEY_ORDINAL; i++) //updates the keyQueues for the KeyDown and KeyUp methods
        {
            if(keys[i])
            {
                keyQueues[i] = true;
            }
            else
            {
                keyQueues[i] = false;
            }
        }
        super.Update();
    }

    public void KeyPressed(KeyEvent event)
    {
        AddEvent(event);
    }
    public void KeyReleased(KeyEvent event)
    {
        AddEvent(event);
    }
    public void KeyTyped(KeyEvent event)
    {
        AddEvent(event);
    }


    //return true while key is being held down
    //returns false if the key is not pressed
    public boolean GetKey(KeyCode key)
    {
        return keys[key.ordinal()];
    }
    //returns true only for the tick in which the key is pressed
    public boolean GetKeyDown(KeyCode key)
    {
        return (keys[key.ordinal()] && !keyQueues[key.ordinal()]);
    }
    //returns true only for the tick in which the key is released
    public boolean GetKeyUp(KeyCode key)
    {
        return (!keys[key.ordinal()] && keyQueues[key.ordinal()]);
    }
}
