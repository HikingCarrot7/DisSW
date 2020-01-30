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
public final class DibujadorEsquema
{

    private Canvas esquema;

    private final int HEIGHT = 525;
    private final int WIDTH = 580;
    private final int MIDDLE = WIDTH / 2;
    private final int CPU_WIDTH = 200;
    private final int CPU_HEIGHT = 200;
    private final int PROCESO_RECT_WIDTH = 30;
    private final int PROCESO_RECT_HEIGHT = 20;
    private final int OFFSET_X = 20;
    private final int SEPARACION_POR_LINEA = 30;
    private final int TINY_TRIANGLE = 5;
    private final int MAX_PROCESOS_LINEA = (WIDTH - OFFSET_X * 2) / PROCESO_RECT_WIDTH - 1;

    private final Rectangle CPU;
    private Proceso procesoActual;
    private long tiempoUsoCPUActual;

    public DibujadorEsquema(Canvas esquema)
    {
        CPU = new Rectangle(MIDDLE - CPU_WIDTH / 2, 10, CPU_WIDTH, CPU_HEIGHT);
        this.esquema = esquema;
    }

    public void init()
    {
        esquema.createBufferStrategy(3);
    }

    public void render()
    {
        BufferStrategy bs = esquema.getBufferStrategy();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);

        dibujarProcesador(g);
        dibujarTiemposEsperaProcesos(g);
        dibujarProcesoActual(g);

        bs.show();
        g.dispose();
    }

    private void dibujarTiemposEsperaProcesos(Graphics2D g)
    {
        int y = 230;
        final int LINE_LENGTH = 5;

        for (int i = 0, x = OFFSET_X; i < MAX_PROCESOS_LINEA; i++, x += PROCESO_RECT_WIDTH)
        {
            g.drawRect(x, y, PROCESO_RECT_WIDTH, PROCESO_RECT_HEIGHT);
            g.drawLine(x, y + PROCESO_RECT_HEIGHT, x, y + PROCESO_RECT_HEIGHT + LINE_LENGTH);
            drawInvertedTriangle(g, x, y + PROCESO_RECT_HEIGHT, TINY_TRIANGLE);
            dibujarStringPunto(g, "55", x, y + PROCESO_RECT_HEIGHT + LINE_LENGTH);
            dibujarStringPunto(g, "P999", x + PROCESO_RECT_WIDTH / 2, y + 3);
        }

        //drawLargeLine(g, OFFSET_X + MAX_PROCESOS_LINEA * PROCESO_RECT_WIDTH, 230 + PROCESO_RECT_HEIGHT / 2);
    }

    private void dibujarProcesador(Graphics2D g)
    {
        final int LINE_LENGTH = 8;

        dibujarRectanguloCentrado(g, CPU);
        dibujarTextoCentradoRect(g, "Intel(R) Core(TM) i9-9990XE", 20, CPU);

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

    public void dibujarProcesoActual(Proceso proceso, long tiempoUsoCpu)
    {
        procesoActual = proceso;
    }

    private void dibujarProcesoActual(Graphics2D g)
    {
        final int BOX_WIDTH = 150;
        final int BOX_HEIGHT = 10;

        if (procesoActual != null)
        {
            dibujarTextoCentradoRect(g, "Proceso actual", 80, CPU);
            dibujarTextoCentradoRect(g, procesoActual.getIdentificador(), 95, CPU);
            g.drawRect(CPU.x + CPU.width / 2 - BOX_WIDTH / 2, 175, BOX_WIDTH, BOX_HEIGHT);
            g.fillRect(CPU.x + CPU.width / 2 - BOX_WIDTH / 2, 175, BOX_WIDTH / 2, BOX_HEIGHT);

        } else
            dibujarTextoCentradoRect(g, "No hay procesos esperando", 80, CPU);

    }

    private void dibujarRectanguloCentrado(Graphics2D g, int y, int width, int height)
    {
        g.drawRect(MIDDLE - width / 2, y, width, height);
    }

    private void dibujarRectanguloCentrado(Graphics2D g, Rectangle rect)
    {
        g.drawRect(MIDDLE - rect.width / 2, rect.y, rect.width, rect.height);
    }

    private void dibujarTextoCentradoRect(Graphics2D g, String text, int y, Rectangle rect)
    {
        FontMetrics fm = g.getFontMetrics();
        Rectangle bounds = fm.getStringBounds(text, g).getBounds();
        g.drawString(text, (float) (rect.x + rect.width / 2 - bounds.getWidth() / 2), rect.y + y);
    }

    private void dibujarStringPunto(Graphics2D g, String text, int x, int y)
    {
        Rectangle rect = g.getFontMetrics().getStringBounds(text, g).getBounds();

        if (text.length() >= 4 && text.matches("^[0-9]+$"))
            g.drawRect(x - rect.width / 2, y, rect.width, rect.height - 4);

        g.drawString(text, x - rect.width / 2, y + rect.height / 2 + 4);
    }

    private void drawLargeLine(Graphics2D g, int x, int y)
    {
        g.drawLine(x, y, WIDTH - OFFSET_X, y);
        g.drawLine(WIDTH - OFFSET_X, y, WIDTH - OFFSET_X, y + PROCESO_RECT_HEIGHT + 10);
        g.drawLine(WIDTH - OFFSET_X, y + PROCESO_RECT_HEIGHT + 10, OFFSET_X - OFFSET_X / 2, y + PROCESO_RECT_HEIGHT + 10);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + PROCESO_RECT_HEIGHT + 10, OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT, OFFSET_X, y + SEPARACION_POR_LINEA + PROCESO_RECT_HEIGHT);
    }

    private void drawTriangle(Graphics2D g, int x, int y, int lenght)
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

    private void drawInvertedTriangle(Graphics2D g, int x, int y, int lenght)
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
