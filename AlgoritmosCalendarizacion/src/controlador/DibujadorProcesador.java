package controlador;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import modelo.Proceso;

/**
 *
 * @author HikingCarrot7
 */
public class DibujadorProcesador
{

    public final int CPU_WIDTH = 200;
    public final int CPU_HEIGHT = 200;

    private final int BOX_WIDTH = 150;
    private final int BOX_HEIGHT = 10;

    private final Rectangle CPU;

    private Proceso procesoActual;
    private final DibujadorEsquema DIBUJADOR_ESQUEMA;
    private long tiempoUsoCPUActual;
    private int pixels_per_tick;
    private int charge_box_length = 0;

    public DibujadorProcesador(DibujadorEsquema dibujadorEsquema)
    {
        CPU = new Rectangle(DibujadorEsquema.MIDDLE - CPU_WIDTH / 2, 10, CPU_WIDTH, CPU_HEIGHT);
        this.DIBUJADOR_ESQUEMA = dibujadorEsquema;
    }

    public void dibujarProcesador(Graphics2D g)
    {
        final int LINE_LENGTH = 8;

        DIBUJADOR_ESQUEMA.dibujarRectanguloCentrado(g, CPU);
        DIBUJADOR_ESQUEMA.dibujarTextoCentradoRect(g, "Intel(R) Core(TM) i5-4570 @3.2 GHz", 20, CPU);

        for (int i = 0; i <= CPU.width; i += 5)
        {
            g.drawLine(CPU.x + i, CPU.y, CPU.x + i, CPU.y - LINE_LENGTH);
            g.drawLine(CPU.x + i, CPU.y + CPU.height, CPU.x + i, CPU.y + CPU.height + LINE_LENGTH);
        }

        for (int i = 0; i <= CPU.height; i += 5)
        {
            g.drawLine(CPU.x, CPU.y + i, CPU.x - LINE_LENGTH, CPU.y + i);
            g.drawLine(CPU.x + CPU.width, CPU.y + i, CPU.x + CPU.width + LINE_LENGTH, CPU.y + i);
        }

    }

    /**
     * @deprecated
     */
    public void updateProcesoActual()
    {
        charge_box_length = 0;
        pixels_per_tick = (int) (150 / (getTiempoUsoCPUActual() * 60 / 1000));
    }

    public void dibujarProcesoActual(Graphics2D g)
    {
        if (procesoActual != null && !procesoActual.esProcesoTerminado())
        {
            DIBUJADOR_ESQUEMA.dibujarTextoCentradoRect(g, "Proceso actual", 80, CPU);
            DIBUJADOR_ESQUEMA.dibujarTextoCentradoRect(g, procesoActual.getIdentificador(), 95, CPU);
            g.drawRect(CPU.x + CPU.width / 2 - BOX_WIDTH / 2, 175, BOX_WIDTH, BOX_HEIGHT);
            g.fillRect(CPU.x + CPU.width / 2 - BOX_WIDTH / 2, 175, charge_box_length += pixels_per_tick, BOX_HEIGHT);

        } else
            DIBUJADOR_ESQUEMA.dibujarTextoCentradoRect(g, "No hay procesos esperando", 80, CPU);

    }

    public Proceso getProcesoActual()
    {
        return procesoActual;
    }

    public void setProcesoActual(Proceso procesoActual)
    {
        this.procesoActual = procesoActual;
    }

    public long getTiempoUsoCPUActual()
    {
        return tiempoUsoCPUActual;
    }

    public void setTiempoUsoCPUActual(long tiempoUsoCPUActual)
    {
        this.tiempoUsoCPUActual = tiempoUsoCPUActual;
    }

}
