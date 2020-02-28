package test;

/**
 *
 * @author HikingCarrot7
 */
public class Logger
{

    public void logOn(Modem modem, String pno, String user, String pw)
    {
        modem.dial(pno);
    }

}
