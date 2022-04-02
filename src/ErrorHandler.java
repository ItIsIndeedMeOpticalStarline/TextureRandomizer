public class ErrorHandler 
{
    static void Assert(boolean cond, String msg, boolean exit)
    {
        if (!cond) { Error(msg, exit); }
    }

    static void Error(String msg, boolean exit)
    {
        if (exit) 
        {
            System.out.println("ERROR: " + msg + "\n");
            System.exit(1);
        }
        else { System.out.println("WARNING: " + msg + "\n"); }
    }
}
