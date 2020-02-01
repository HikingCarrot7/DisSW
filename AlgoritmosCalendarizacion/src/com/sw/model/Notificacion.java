package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Notificacion
{

    public static final int PROCESO_ENTRO_CPU = 0;
    public static final int PROCESO_DEJO_CPU = 1;
    public static final int PROCESO_HA_FINALIZADO = 2;
    public static final int NO_QUEDAN_PROCESOS = 3;

    private int identificador;
    private Proceso proceso;
    private long tiempoUsoCpu;
    private long tiempoTranscurrido;

    public Notificacion(int identificador, Proceso proceso, long tiempoUsoCpu)
    {
        this.identificador = identificador;
        this.proceso = proceso;
        this.tiempoUsoCpu = tiempoUsoCpu;
    }

    public Notificacion(int identificador, Proceso proceso, long tiempoUsoCpu, long tiempoTranscurrido)
    {
        this(identificador, proceso, tiempoUsoCpu);
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public long getTiempoUsoCpu()
    {
        return tiempoUsoCpu;
    }

    public void setTiempoUsoCpu(long tiempoUsoCpu)
    {
        this.tiempoUsoCpu = tiempoUsoCpu;
    }

    public Proceso getProceso()
    {
        return proceso;
    }

    public void setProceso(Proceso proceso)
    {
        this.proceso = proceso;
    }

    public int getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public long getTiempoTranscurrido()
    {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(long tiempoTranscurrido)
    {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

}
