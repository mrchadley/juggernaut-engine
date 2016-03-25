package engine;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import engine.game_objects.Vector2;

/*
clean up the code in here
fix the get event methods

*/
public class J_InputHandler implements EventHandler<InputEvent>
{
    private static J_InputHandler instance = new J_InputHandler();

    private final int MAX_KEY_ORDINAL = 224;
    private boolean[] keys = new boolean[MAX_KEY_ORDINAL];
    private boolean[] keyQueues = new boolean[MAX_KEY_ORDINAL];

    private final int MAX_BUTTON_ORDINAL = 4;
    private boolean[] buttons = new boolean[MAX_BUTTON_ORDINAL];
    private boolean[] buttonQueues = new boolean[MAX_BUTTON_ORDINAL];
    private Vector2 mousePosition;

    private double deltaWheelRotation = 0;
    private double wheelRotation = 0;

    private J_InputHandler()
    { }

    @Override
    public void handle(InputEvent event)
    {
        //J_Log.debug("j_inputHandler", event.getEventType().getName());

        if(event.getEventType() == KeyEvent.KEY_PRESSED)//key being held
        {
            KeyEvent keyEvent = (KeyEvent) event;
            keys[keyEvent.getCode().ordinal()] = true;
        }
        if(event.getEventType() == KeyEvent.KEY_RELEASED)
        {
            KeyEvent keyEvent = (KeyEvent) event;
            keys[keyEvent.getCode().ordinal()] = false;
        }

        if(event.getEventType() == MouseEvent.ANY)
        {
            MouseEvent mouseEvent = (MouseEvent)event;
            mousePosition = new Vector2((float) mouseEvent.getX(), (float) mouseEvent.getY());
        }
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
        {
            MouseEvent mouseEvent = (MouseEvent)event;
            buttons[mouseEvent.getButton().ordinal()] = true;
        }
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
        {
            MouseEvent mouseEvent = (MouseEvent)event;
            buttons[mouseEvent.getButton().ordinal()] = false;
        }

        if(event.getEventType() == ScrollEvent.ANY)
        {
            ScrollEvent scrollEvent = (ScrollEvent)event;
            deltaWheelRotation += scrollEvent.getDeltaY();
            wheelRotation += scrollEvent.getDeltaY();
        }
    }

    //@Override
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

        for(int i = 0; i < MAX_BUTTON_ORDINAL; i++) //update mouseQueues
        {
            if(buttons[i])
            {
                buttonQueues[i] = true;
            }
            else
            {
                buttonQueues[i] = false;
            }
        }

        deltaWheelRotation = 0.0;

        //super.Update();
    }


    /*
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

    public void MouseClicked(MouseEvent event)
    {
        AddEvent(event);
    }
    public void MousePressed(MouseEvent event)
    {
        AddEvent(event);
    }
    public void MouseReleased(MouseEvent event)
    {
        AddEvent(event);
    }
    public void MouseEntered(MouseEvent event)
    {
        AddEvent(event);
    }
    public void MouseExited(MouseEvent event)
    {
        AddEvent(event);
    }
    public void MouseDragged(MouseEvent event)
    {
        AddEvent(event);
    }

    public void MouseWheelMoved(ScrollEvent event)
    {
        AddEvent(event);
    }
    */

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

    //returns position vector of the mouse
    public Vector2 GetMousePosition()
    {
        return mousePosition;
    }
    //returns true while the button is pressed and false after it's been released
    public boolean GetButton(MouseButton button)
    {
        return buttons[button.ordinal()];
    }
    //returns true only during the tick in which the button was pressed
    public boolean GetButtonDown(MouseButton button)
    {
        return (buttons[button.ordinal()] && !buttonQueues[button.ordinal()]);
    }
    //returns true only during the tick in which the button was released
    public boolean GetButtonUp(MouseButton button)
    {
        return (buttons[button.ordinal()] && !buttonQueues[button.ordinal()]);
    }

    //returns the change in wheel rotation
    public double GetDeltaWheelRotation()
    {
        return deltaWheelRotation;
    }
    //returns the overall wheel rotation
    public double GetWheelRotation()
    {
        return wheelRotation;
    }

    public static J_InputHandler GetInstance()
    {
        return instance;
    }
}
