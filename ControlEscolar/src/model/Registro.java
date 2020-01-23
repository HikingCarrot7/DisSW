package model;

/**
 * @author HikingCarrot7
 */
public class Registro
{

    private int claveMaestro;
    private int claveAsignatura;
    private int matricula;

    public Registro(int claveMaestro, int claveAsignatura, int matricula)
    {
        this.claveMaestro = claveMaestro;
        this.claveAsignatura = claveAsignatura;
        this.matricula = matricula;
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

    public int getMatricula()
    {
        return matricula;
    }

    public void setMatricula(int matricula)
    {
        this.matricula = matricula;
    }
}
