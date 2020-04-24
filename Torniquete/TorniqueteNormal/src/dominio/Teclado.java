package dominio;

import java.util.Scanner;

/**
 *
 * @author Nicolás
 */
public class Teclado
{

    private final Scanner teclado;

    public Teclado()
    {
        teclado = new Scanner(System.in);
    }

    public String getOpcionUsuario()
    {
        String entrada = teclado.nextLine();

        switch (entrada.trim())
        {
            case Opcion.MONEDA:
                return Opcion.MONEDA;
            case Opcion.PASAR:
                return Opcion.PASAR;
            default:
                throw new OpcionNoValidaException();
        }

    }

}
