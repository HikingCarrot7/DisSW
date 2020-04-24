package test;

import dominio.Estado;
import dominio.Opcion;
import dominio.OpcionNoValidaException;
import dominio.Torniquete;

/**
 *
 * @author Nicolás
 */
public class Prueba
{

    public static void main(String[] args)
    {
        Torniquete torniquete = new Torniquete(new Estado(Estado.BLOQUEDA));

        while (torniquete.estaEncendido())
        {
            torniquete.getPantalla().mostrarMenu();

            try
            {
                switch (torniquete.getTeclado().getOpcionUsuario())
                {
                    case Opcion.MONEDA:
                        torniquete.moneda();
                        break;
                    case Opcion.PASAR:
                        torniquete.pasar();
                        break;
                }

            } catch (OpcionNoValidaException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
}
