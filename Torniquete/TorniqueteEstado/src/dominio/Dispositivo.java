package dominio;

/**
 *
 * @author Nicolás
 */
public class Dispositivo
{

    public void bloquear()
    {
        System.out.println("La máquina está bloqueada");
    }

    public void desbloquear()
    {
        System.out.println("La máquina está desbloqueada");
    }

    public void gracias()
    {
        System.out.println("Gracias");
    }

    public void alarma()
    {
        System.out.println("Alarma!");
    }

    public void reiniciarAlarma()
    {
        System.out.println("La alarma se ha reiniciado.");
    }
}
