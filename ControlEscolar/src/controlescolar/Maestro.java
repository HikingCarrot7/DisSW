package controlescolar;

/**
 *
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{

    private int clave;

    public Maestro(int clave, String nombre, String apellido)
    {

        super(nombre, apellido);

        this.clave = clave;

    }

    public int getClave()
    {
        return clave;
    }

    public void setClave(int clave)
    {
        this.clave = clave;
    }

}
