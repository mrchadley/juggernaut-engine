package juggernaut_engine;

import java.io.PrintStream;

public class J_Logger
{
    public enum Level
    {
        DEBUG("Debug", 0),
        INFO("Info", 1),
        WARNING("Warning", 2),
        ERROR("Error", 3);

        private String name;
        private int priority;

        private Level(String name, int priority)
        {
            this.name = name;
            this.priority = priority;
        }
        public String GetName()
        {
            return name;
        }
        public int GetPriority()
        {
            return priority;
        }
    }

    private static Level MIN_LEVEL = Level.DEBUG;
    private static PrintStream P_STREAM = null;

    public static void debug(String module, String message)
    {
        LogMessage(Level.DEBUG, module, message);
    }
    public static void info(String module, String message)
    {
        LogMessage(Level.INFO, module, message);
    }
    public static void warning(String module, String message)
    {
        LogMessage(Level.WARNING, module, message);
    }
    public static void error(String module, String message)
    {
        LogMessage(Level.ERROR, module, message);
    }

    public static Level GetMinPriorityLevel()
    {
        return J_Logger.MIN_LEVEL;
    }
    public static synchronized void SetMinPriorityLevel(Level level)
    {
        J_Logger.MIN_LEVEL = level;
    }

    public static PrintStream GetPrintStream()
    {
        return J_Logger.P_STREAM;
    }
    public static synchronized void SetPrintStream(PrintStream pStream)
    {
        J_Logger.P_STREAM = pStream;
    }

    public static synchronized void LogMessage(Level level, String module, String message)
    {
        if(level.GetPriority() >= MIN_LEVEL.GetPriority())
        {
            String logMessage = level.GetName() + " (" + module + "): \n" + message;

            if(P_STREAM == null)
            {
                System.err.println(logMessage);
                System.err.flush();
            }
            else
            {
                P_STREAM.println(logMessage);
                P_STREAM.flush();
            }
        }
    }
}
