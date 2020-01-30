package controlador;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author HikingCarrot7
 */
public final class Esquema extends JPanel
{

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

    private Rectangle cpu;

    public Esquema()
    {
        cpu = new Rectangle(MIDDLE - CPU_WIDTH / 2, 10, CPU_WIDTH, CPU_HEIGHT);
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        dibujarProcesador(g2d);
        dibujarTiemposEsperaProcesos(g2d);
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

        drawLargeLine(g, OFFSET_X + MAX_PROCESOS_LINEA * PROCESO_RECT_WIDTH, 230 + PROCESO_RECT_HEIGHT / 2);

    }

    private void dibujarProcesador(Graphics2D g)
    {
        final int LINE_LENGTH = 8;

        dibujarRectanguloCentrado(g, cpu);
        dibujarTextoCentradoRect(g, "Core i9 999999 xe", 20, cpu);

        for (int i = 0; i <= cpu.width; i += 5)
        {
            g.drawLine(cpu.x + i, cpu.y, cpu.x + i, cpu.y - LINE_LENGTH);
            g.drawLine(cpu.x + i, cpu.y + cpu.height, cpu.x + i, cpu.y + cpu.height + LINE_LENGTH);
        }

        for (int i = 0; i <= cpu.height; i += 5)
        {
            g.drawLine(cpu.x, cpu.y + i, cpu.x - LINE_LENGTH, cpu.y + i);
            g.drawLine(cpu.x + cpu.width, cpu.y + i, cpu.x + cpu.width + LINE_LENGTH, cpu.y + i);
        }

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
