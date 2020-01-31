package controlador;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferStrategy;
import modelo.Proceso;

/**
 *
 * @author HikingCarrot7
 */
public class DibujadorEsquema
{

    private final Canvas ESQUEMA;
    private final DibujadorProcesador DIBUJADOR_PROCESADOR;
    private final DibujadorProcesos DIBUJADOR_PROCESOS;

    public static final int HEIGHT = 525;
    public static final int WIDTH = 580;
    public static final int MIDDLE = WIDTH / 2;

    public DibujadorEsquema(Canvas esquema)
    {

        this.ESQUEMA = esquema;
        DIBUJADOR_PROCESADOR = new DibujadorProcesador(this);
        DIBUJADOR_PROCESOS = new DibujadorProcesos(this);
    }

    public void init()
    {
        ESQUEMA.createBufferStrategy(3);
    }

    public void render()
    {
        BufferStrategy bs = ESQUEMA.getBufferStrategy();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);

        DIBUJADOR_PROCESADOR.dibujarProcesador(g);
        DIBUJADOR_PROCESOS.dibujarTiemposEsperaProcesos(g);
        DIBUJADOR_PROCESADOR.dibujarProcesoActual(g);

        bs.show();
        g.dispose();
    }

    public void dibujarProcesoActual(Proceso proceso, long tiempoUsoCpu, long tiempoTranscurrido)
    {
        if (proceso.esProcesoTerminado())
        {
            DIBUJADOR_PROCESADOR.setProcesoActual(null);
            DIBUJADOR_PROCESOS.anadirProcesoProcesado(proceso, tiempoTranscurrido);
            return;
        }

        DIBUJADOR_PROCESADOR.setProcesoActual(proceso);
        DIBUJADOR_PROCESADOR.setTiempoUsoCPUActual(tiempoUsoCpu);
        DIBUJADOR_PROCESOS.anadirProcesoProcesado(proceso, tiempoTranscurrido);
        //DIBUJADOR_PROCESADOR.updateProcesoActual();
    }

    public void dibujarRectanguloCentrado(Graphics2D g, int y, int width, int height)
    {
        g.drawRect(MIDDLE - width / 2, y, width, height);
    }

    public void dibujarRectanguloCentrado(Graphics2D g, Rectangle rect)
    {
        g.drawRect(MIDDLE - rect.width / 2, rect.y, rect.width, rect.height);
    }

    public void dibujarTextoCentradoRect(Graphics2D g, String text, int y, Rectangle rect)
    {
        FontMetrics fm = g.getFontMetrics();
        Rectangle bounds = fm.getStringBounds(text, g).getBounds();
        g.drawString(text, (float) (rect.x + rect.width / 2 - bounds.getWidth() / 2), rect.y + y);
    }

    public void dibujarStringPunto(Graphics2D g, String text, int x, int y)
    {
        Rectangle rect = g.getFontMetrics().getStringBounds(text, g).getBounds();

        if (text.length() >= 4 && text.matches("^[0-9]+$"))
            g.drawRect(x - rect.width / 2, y, rect.width, rect.height - 4);

        g.drawString(text, x - rect.width / 2, y + rect.height / 2 + 4);
    }

    public void drawTriangle(Graphics2D g, int x, int y, int lenght)
    {
        int offset = (int) Math.sqrt(Math.pow(lenght, 2) - Math.pow((lenght / 2), 2));

        int[] puntosX =
        {
            x - offset, x, x - offset
        };

        int[] puntosY =
        {
            y - offset, y, y + offset
        };

        GeneralPath triangle = new GeneralPath();

        triangle.moveTo(puntosX[0], puntosY[0]);

        for (int i = 0; i < puntosX.length; i++)
            triangle.lineTo(puntosX[i], puntosY[i]);

        triangle.closePath();
        g.fill(triangle);
    }

    public void drawInvertedTriangle(Graphics2D g, int x, int y, int lenght)
    {
        int offset = (int) Math.sqrt(Math.pow(lenght, 2) - Math.pow((lenght / 2), 2));

        int[] puntosX =
        {
            x + offset, x, x + offset
        };

        int[] puntosY =
        {
            y + offset, y, y - offset
        };

        GeneralPath triangle = new GeneralPath();

        triangle.moveTo(puntosX[0], puntosY[0]);

        for (int i = 0; i < puntosX.length; i++)
            triangle.lineTo(puntosX[i], puntosY[i]);

        triangle.closePath();
        g.fill(triangle);
    }

}
