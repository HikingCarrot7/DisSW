package controlador;

/**
 *
 * @author HikingCarrot7
 */
public class Loop implements Runnable
{

    private final DibujadorEsquema esquema;

    public Loop(DibujadorEsquema esquema)
    {
        this.esquema = esquema;
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        esquema.init();

        long lastTime = System.nanoTime();
        final double amountOfThicks = 60.0;
        double ns = 1000000000 / amountOfThicks;
        double delta = 0;

        Long timer = System.currentTimeMillis();

        while (true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1)
            {
                esquema.render();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000)
                timer += 1000;
        }

    }

}
