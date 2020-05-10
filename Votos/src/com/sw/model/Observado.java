package com.sw.model;

import java.util.ArrayList;

/**
 *
 * @author Nicolás
 */
public class Observado
{

    private final ArrayList<Observador> obs;

    public Observado()
    {
        obs = new ArrayList<>();
    }

    public void addObservador(Observador o)
    {
        if (o == null)
            throw new NullPointerException();

        if (!obs.contains(o))
            obs.add(o);
    }

    public void removerObservador(Observador o)
    {
        obs.remove(o);
    }

    public void notificarObservadores(Object arg)
    {
        for (int i = obs.size() - 1; i >= 0; i--)
            obs.get(i).update(this, arg);
    }

}
