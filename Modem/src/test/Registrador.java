package test;

/**
 *
 * @author HikingCarrot7
 */
public class Registrador
{

    public void iniciarSesion(Modem modem, String pno, String user, String pw)
    {
        modem.dial(pno);
    }

}
