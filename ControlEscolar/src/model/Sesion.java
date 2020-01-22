package model;

import java.util.Date;

/**
 *
 * @author HikingCarrot7
 */
public class Sesion
{

    private Dia dia;
    private Date inicioClase;
    private Date finalClase;

    public Sesion(Dia dia, Date inicioClase, Date finalClase)
    {
        this.dia = dia;
        this.inicioClase = inicioClase;
        this.finalClase = finalClase;
    }

    public Dia getDia()
    {
        return dia;
    }

    public void setDia(Dia dia)
    {
        this.dia = dia;
    }

    public Date getInicioClase()
    {
        return inicioClase;
    }

    public void setInicioClase(Date inicioClase)
    {
        this.inicioClase = inicioClase;
    }

    public Date getFinalClase()
    {
        return finalClase;
    }

    public void setFinalClase(Date finalClase)
    {
        this.finalClase = finalClase;
    }

}
