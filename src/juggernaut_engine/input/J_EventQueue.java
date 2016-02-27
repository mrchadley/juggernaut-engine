package juggernaut_engine.input;

import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.Collection;
import java.util.LinkedList;

public abstract class J_EventQueue<T extends Event> implements EventHandler<T>
{
    LinkedList<T> events = new LinkedList<>();
    LinkedList<T> eventQueue = new LinkedList<>();

    public synchronized void Update()
    {
        for(T event : eventQueue)
        {
            handle(event);
        }
        events = eventQueue;
        eventQueue = new LinkedList<>();
    }

    public synchronized void AddEvent(T event)
    {
        eventQueue.add(event);
    }

    public synchronized void AddEvents(Collection<T> newEvents)
    {
        eventQueue.addAll(newEvents);
    }
}
