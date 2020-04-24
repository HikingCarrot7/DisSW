package dominio;

/**
 *
 * @author Nicolás
 */
public class Pantalla
{

    public void mostrarMenu()
    {
        System.out.println("\n1.-> Insertar moneda.\n2.-> Pasar.");
    }

    public void mostrarMensaje(String mensaje)
    {
        System.out.println("\n" + mensaje);
    }

}
