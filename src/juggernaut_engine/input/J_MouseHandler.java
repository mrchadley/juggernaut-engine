package juggernaut_engine.input;

import engine.framework.Vector2;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class J_MouseHandler extends J_EventQueue<MouseEvent>
{
    private Vector2 mousePosition;
    private boolean leftButton;
    private boolean middleButton;
    private boolean rightButton;

    protected static final int MAX_BUTTON_ORDINAL = 4;
    private boolean[] buttons = new boolean[MAX_BUTTON_ORDINAL];
    private boolean[] buttonQueues = new boolean[MAX_BUTTON_ORDINAL];

    @Override
    public void handle(MouseEvent event)
    {
        mousePosition = new Vector2((float)event.getX(), (float)event.getY());

        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
        {
            buttons[event.getButton().ordinal()] = true;
        }
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
        {
            buttons[event.getButton().ordinal()] = false;
        }
    }

    @Override
    public void Update()
    {
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
        super.Update();
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
}
