package test;

/**
 *
 * @author HikingCarrot7
 */
public class Test
{

    public static void main(String[] args)
    {
        Logger logger = new Logger();

        logger.logOn(new Hayes(), "hola", "juan", "admin123");
        logger.logOn(new Courrier(), "hola", "juan", "admin123");
        logger.logOn(new Ernie(), "hola", "juan", "admin123");
    }
}
