package juggernaut_engine;

import javafx.application.Application;

public abstract class J_Loop extends Application
{
    private boolean running = false;

    public void Run(double delta) {
        running = true;
        Startup();

        double nextTime = (double) System.nanoTime() / 1000000000.0;
        double maxTimeDiff = 0.5;
        int skippedFrames = 1;
        int maxSkippedFrames = 5;
        while (running) {
            // convert the time to seconds
            double currTime = (double) System.nanoTime() / 1000000000.0;
            if ((currTime - nextTime) > maxTimeDiff) nextTime = currTime;
            if (currTime >= nextTime) {
                // assign the time for the next update
                nextTime += delta;
                Update();
                if ((currTime < nextTime) || (skippedFrames > maxSkippedFrames)) {
                    Draw();
                    skippedFrames = 1;
                } else {
                    skippedFrames++;
                }
            } else {
                // calculate the time to sleep
                int sleepTime = (int) (1000.0 * (nextTime - currTime));
                // sanity check
                if (sleepTime > 0) {
                    // sleep until the next update
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        }
        Shutdown();
    }
    public void Stop()
    {
        running = false;
    }

    public abstract void Startup();
    public abstract void Shutdown();
    public abstract void Update();
    public abstract void Draw();
}
