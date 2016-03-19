package juggernaut_engine.input;

import juggernaut_engine.framework.Vector2;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class J_Input
{
    private J_KeyboardHandler keyboard;
    private J_MouseHandler mouse;
    private J_ScrollHandler scrollWheel;

    public J_Input()
    {
        keyboard = new J_KeyboardHandler();
        mouse = new J_MouseHandler();
        scrollWheel = new J_ScrollHandler();
    }

    //keyboard getters
    public boolean GetKeyDown(KeyCode key)
    {
        return keyboard.GetKeyDown(key);
    }
    public boolean GetKeyUp(KeyCode key)
    {
        return keyboard.GetKeyUp(key);
    }
    public boolean GetKey(KeyCode key)
    {
        return keyboard.GetKey(key);
    }

    //mouse getters
    public Vector2 GetMousePosition()
    {
        return mouse.GetMousePosition();
    }
    public boolean GetButtonDown(MouseButton button)
    {
        return mouse.GetButtonDown(button);
    }
    public boolean GetButtonUp(MouseButton button)
    {
        return mouse.GetButtonUp(button);
    }
    public boolean GetButton(MouseButton button)
    {
        return mouse.GetButton(button);
    }

    //scroll getters
    public double GetDeltaWheelRotation()
    {
        return scrollWheel.GetDeltaWheelRotation();
    }
    public double GetWheelRotation()
    {
        return scrollWheel.GetWheelRotation();
    }

    //handler getters
    public J_KeyboardHandler GetKeyboard()
    {
        return keyboard;
    }
    public J_MouseHandler GetMouse()
    {
        return mouse;
    }
    public J_ScrollHandler GetScrollWheel()
    {
        return scrollWheel;
    }

    public void Update()
    {
        keyboard.Update();
        mouse.Update();
        scrollWheel.Update();
    }
}
