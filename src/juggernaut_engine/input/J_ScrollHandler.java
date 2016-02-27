package juggernaut_engine.input;

import javafx.scene.input.ScrollEvent;

public class J_ScrollHandler extends J_EventQueue<ScrollEvent>
{
    private double deltaWheelRotation = 0;
    private double wheelRotation = 0;

    @Override
    public void Update()
    {
        deltaWheelRotation = 0.0;
        super.Update();
    }

    @Override
    public void handle(ScrollEvent event)
    {
        deltaWheelRotation += event.getDeltaY();
        wheelRotation += event.getDeltaY();
    }

    public void MouseWheelMoved(ScrollEvent event)
    {
        AddEvent(event);
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
}
