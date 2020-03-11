package test;

/**
 *
 * @author HikingCarrot7
 */
public class Test
{

    public static void main(String[] args)
    {
        Registrador registrador = new Registrador();

        registrador.iniciarSesion(new Hayes(), "hola", "juan", "admin123");
        registrador.iniciarSesion(new Courrier(), "hola", "juan", "admin123");
        registrador.iniciarSesion(new Ernie(), "hola", "juan", "admin123");
    }
}
