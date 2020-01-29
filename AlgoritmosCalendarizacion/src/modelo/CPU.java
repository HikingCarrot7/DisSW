package modelo;

/**
 *
 * @author HikingC7
 */
public class CPU implements Runnable
{

    private boolean ocupado;
    private Proceso procesoActual;

    public void ejecutarProceso(Proceso proceso)
    {
        System.out.println("El CPU ha recibido el proceso: " + proceso.getIdentificador());
        procesoActual = proceso;
        ocupado = true;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(procesoActual.getTiempoRafaga());
            ocupado = false;
            System.out.println("El CPU ha terminado de ejecutuar el proceso: " + procesoActual.getIdentificador());

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

}
