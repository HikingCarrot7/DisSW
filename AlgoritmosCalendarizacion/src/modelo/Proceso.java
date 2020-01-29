package modelo;

/**
 *
 * @author HikingC7
 */
public abstract class Proceso
{

    private String identificador;
    private final PCB PCB;

    public Proceso(Estado estadoProceso, String identificador, int numProceso, long tiempoRafaga)
    {
        this.identificador = identificador;
        PCB = new PCB(estadoProceso, numProceso, tiempoRafaga);
    }

    public String getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(String identificador)
    {
        this.identificador = identificador;
    }

    public PCB PCB()
    {
        return PCB;
    }

    public boolean procesoTerminado()
    {
        return PCB.getEstadoProceso().equals(Estado.TERMINADO);
    }

}
