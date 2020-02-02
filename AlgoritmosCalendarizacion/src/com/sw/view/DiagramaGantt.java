package com.sw.view;

import com.sw.model.Proceso;
import static com.sw.view.DibujadorEsquema.WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class DiagramaGantt
{

    public static final int PROCESO_RECT_WIDTH = 40;
    public static final int PROCESO_RECT_HEIGHT = 20;
    public static final int OFFSET_X = 20;
    public static final int OFFSET_Y = 230;
    public static final int SEPARACION_POR_LINEA = 30;
    public static final int TINY_TRIANGLE = 5;
    public static final int MAX_PROCESOS_COL = (WIDTH - OFFSET_X * 2) / PROCESO_RECT_WIDTH;
    public static final int MAX_PROCESOS_ROW = 6;
    public static final int MAX_PROCESOS = MAX_PROCESOS_COL * MAX_PROCESOS_ROW;

    private final DibujadorEsquema DIBUJADOR_ESQUEMA;
    private final ArrayList<Proceso> PROCESOS_FINALIZADOS;

    public DiagramaGantt(DibujadorEsquema dibujadorEsquema)
    {
        this.DIBUJADOR_ESQUEMA = dibujadorEsquema;
        PROCESOS_FINALIZADOS = new ArrayList<>();
    }

    public void anadirProcesoFinalizadoAlDiagramaGantt(Proceso proceso, long tiempoTranscurrido)
    {
        proceso.PCB.setTiempoEjecutado(tiempoTranscurrido);
        PROCESOS_FINALIZADOS.add(proceso);
    }

    public void dibujarTiemposEsperaProcesos(Graphics2D g)
    {
        int y = OFFSET_Y;

        g.drawString("Diagrama de Gantt", OFFSET_X, OFFSET_Y - 5);

        for (int i = 0, x = OFFSET_X; i < PROCESOS_FINALIZADOS.size(); i++, x += PROCESO_RECT_WIDTH)
        {
            Proceso proceso = PROCESOS_FINALIZADOS.get(i);

            dibujarRectanguloProceso(g, proceso, x, y);
            dibujarInfoProceso(g, proceso, x, y);

            if ((i + 1) % MAX_PROCESOS_COL == 0 && PROCESOS_FINALIZADOS.size() - (i + 1) > 0)
            {
                drawLargeLine(g, OFFSET_X + MAX_PROCESOS_COL * PROCESO_RECT_WIDTH, y + PROCESO_RECT_HEIGHT / 2);
                x = OFFSET_X - PROCESO_RECT_WIDTH;
                y += PROCESO_RECT_HEIGHT + SEPARACION_POR_LINEA;
            }

        }

        if (PROCESOS_FINALIZADOS.size() >= MAX_PROCESOS)
            for (int i = 0; i < MAX_PROCESOS_COL; i++)
                PROCESOS_FINALIZADOS.remove(0);

    }

    private void dibujarRectanguloProceso(Graphics2D g, Proceso proceso, int x, int y)
    {
        if (proceso.esProcesoTerminado())
        {
            g.setColor(Color.RED);
            g.fillRect(x, y, PROCESO_RECT_WIDTH, PROCESO_RECT_HEIGHT);
            g.setColor(Color.BLACK);

        }

        g.drawRect(x, y, PROCESO_RECT_WIDTH, PROCESO_RECT_HEIGHT);
    }

    private void dibujarInfoProceso(Graphics2D g, Proceso proceso, int x, int y)
    {
        final int LINE_LENGTH = 5;

        g.drawLine(x, y + PROCESO_RECT_HEIGHT, x, y + PROCESO_RECT_HEIGHT + LINE_LENGTH);
        DIBUJADOR_ESQUEMA.drawInvertedTriangle(g, x, y + PROCESO_RECT_HEIGHT, TINY_TRIANGLE);
        DIBUJADOR_ESQUEMA.dibujarStringPunto(g, String.valueOf(proceso.PCB.getTiempoEjecutado()), x, y + PROCESO_RECT_HEIGHT + LINE_LENGTH);
        DIBUJADOR_ESQUEMA.dibujarStringPunto(g, proceso.getIdentificador(), x + PROCESO_RECT_WIDTH / 2, y + 3);
    }

    public void drawLargeLine(Graphics2D g, int x, int y)
    {
        g.drawLine(x, y, WIDTH - OFFSET_X, y);
        g.drawLine(WIDTH - OFFSET_X, y, WIDTH - OFFSET_X, y + PROCESO_RECT_HEIGHT + 10);
        g.drawLine(WIDTH - OFFSET_X, y + PROCESO_RECT_HEIGHT + 10, OFFSET_X - OFFSET_X / 2, y + PROCESO_RECT_HEIGHT + 10);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + PROCESO_RECT_HEIGHT + 10, OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT, OFFSET_X, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT);
    }

}
