package model;

/**
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{

    private int claveMaestro;

    public Maestro(int clave, String nombre, String apellido)
    {
        super(nombre, apellido);
        this.claveMaestro = clave;
    }

    public int getClaveMaestro()
    {
        return claveMaestro;
    }

    public void setClave(int clave)
    {
        this.claveMaestro = clave;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.claveMaestro;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Maestro other = (Maestro) obj;
        return this.claveMaestro == other.claveMaestro;
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s", getClaveMaestro(), getNombre(), getApellido());
    }
}
