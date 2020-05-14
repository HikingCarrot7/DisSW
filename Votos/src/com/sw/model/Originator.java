package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class Originator
{

    private int[] actualState;

    public void setActualState(int[] actualState)
    {
        this.actualState = actualState;
    }

    public Memento storeInMemento()
    {
        return new Memento(actualState);
    }

    public int[] restoreFromMemento(Memento memento)
    {
        actualState = memento.getSavedState();
        return actualState;
    }
}
