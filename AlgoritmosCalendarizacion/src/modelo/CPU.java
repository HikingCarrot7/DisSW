package modelo;

/**
 *
 * @author HikingC7
 */
public class CPU implements Runnable
{

    private boolean ocupado;
    private volatile boolean procesoInterrumpido;
    private Proceso procesoActual;

    public void ejecutarProceso(Proceso proceso)
    {
        procesoActual = proceso;
        ocupado = true;
        procesoInterrumpido = false;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        try
        {
            long tiempoEjecutado = procesoActual.PCB().getTiempoEjecutado();

            for (int i = 0; (i < procesoActual.PCB().getTiempoRafaga() - tiempoEjecutado) && !procesoInterrumpido; i++)
            {
                Thread.sleep(1);
                procesoActual.PCB().aumentarTiempoEjecutado();
            }

            ocupado = false;

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public boolean isOcupado()
    {
        return ocupado;
    }

    public void setOcupado(boolean ocupado)
    {
        this.ocupado = ocupado;
    }

    public Proceso getProcesoActual()
    {
        return procesoActual;
    }

    public void setProcesoActual(Proceso procesoActual)
    {
        this.procesoActual = procesoActual;
    }

    public void interrumpirProceso()
    {
        procesoInterrumpido = true;
    }

}
