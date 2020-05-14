package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class Memento
{

    private final int[] votos;

    public Memento(int[] votos)
    {
        this.votos = votos;
    }

    public int[] getSavedState()
    {
        return votos;
    }

}
