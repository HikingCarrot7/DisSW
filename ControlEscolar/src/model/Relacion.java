package model;

/**
 *
 * @author HikingCarrot7
 */
public class Relacion
{

    private int claveMaestro;
    private int claveAsignatura;

    public Relacion(int claveMaestro, int claveAsignatura)
    {
        this.claveMaestro = claveMaestro;
        this.claveAsignatura = claveAsignatura;

    }

    public int getClaveMaestro()
    {
        return claveMaestro;
    }

    public void setClaveMaestro(int claveMaestro)
    {
        this.claveMaestro = claveMaestro;
    }

    public int getClaveAsignatura()
    {
        return claveAsignatura;
    }

    public void setClaveAsignatura(int claveAsignatura)
    {
        this.claveAsignatura = claveAsignatura;
    }

    @Override
    public String toString()
    {
        return getClaveMaestro() + "," + getClaveAsignatura();
    }

}
