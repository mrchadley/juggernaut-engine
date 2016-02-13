package engine;


public class JuggernautHub implements Runnable
{
    private Thread thread;

    private double frameCap = 1 / 60; //framerate
    private boolean isRunning;


    JuggernautHub()
    {

    }
    public void start()
    {
        if(isRunning)
            return;

        thread = new Thread(this);
        thread.run();
    }

    public void stop()
    {
        if(!isRunning)
            return;

        isRunning = false;
    }

    public void run()
    {
        isRunning = true;

        double time1 = 0;
        double time2 = System.nanoTime() / 1000000000.0;
        double deltaTime = 0;
        double unprocessedTime = 0;

        while(isRunning) //game loop
        {
            boolean render = false;

            time1 = System.nanoTime() / 1000000000.0;
            deltaTime = time1 - time2;
            time2 = time1;

            unprocessedTime += deltaTime;
            while(unprocessedTime >= frameCap)
            {
                //update game
                unprocessedTime -= frameCap;
                render = true;
            }
            if(render)
            {
                //clear screen
                //render game
                //update window
            }
            else
            {
                try
                {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        cleanUp();
    }


    public void cleanUp()
    {

    }
}
