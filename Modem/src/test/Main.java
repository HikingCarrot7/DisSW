package test;

/**
 *
 * @author HikingCarrot7
 */
public class Main
{

    public Main()
    {
        logOn(new Hayes(), "hola", "juan", "admin123");
        logOn(new Courrier(), "hola", "juan", "admin123");
        logOn(new Ernie(), "hola", "juan", "admin123");
    }

    public void logOn(TipoModem m, String pno, String user, String pw)
    {
        if (m.getClass() == Hayes.class)
            dialHayes((Hayes) m, pno);

        if (m.getClass() == Courrier.class)
            dialCourrier((Courrier) m, pno);

        if (m.getClass() == Ernie.class)
            dialErnie((Ernie) m, pno);
    }

    private void dialHayes(Hayes m, String pno)
    {
        System.out.println("Dialing original.Hayes");
    }

    private void dialErnie(Ernie m, String pno)
    {
        System.out.println("Dialing original.Ernie");
    }

    private void dialCourrier(Courrier m, String pno)
    {
        System.out.println("Dialing original.Courrier");
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
