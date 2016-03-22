package engine;

import juggernaut_engine.J_Log;

/**
 * Created by marct on 2016-03-20.
 */
public class testObject implements test
{
    @Override
    public void Update() {
        J_Log.debug("testObject", "updating?");
    }
}
