package controlescolar;

/**
 *
 * @author HikingCarrot7
 */
public class Asignatura
{

    private int clave;
    private String nombreAsinatura;
    private String licenciatura;

    public Asignatura(int clave, String nombreAsinatura, String licenciatura)
    {
        this.clave = clave;
        this.nombreAsinatura = nombreAsinatura;
        this.licenciatura = licenciatura;
    }

    public int getClave()
    {
        return clave;
    }

    public void setClave(int clave)
    {
        this.clave = clave;
    }

    public String getNombreAsinatura()
    {
        return nombreAsinatura;
    }

    public void setNombreAsinatura(String nombreAsinatura)
    {
        this.nombreAsinatura = nombreAsinatura;
    }

    public String getLicenciatura()
    {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura)
    {
        this.licenciatura = licenciatura;
    }

}
