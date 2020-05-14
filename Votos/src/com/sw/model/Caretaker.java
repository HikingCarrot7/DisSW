package com.sw.model;

import java.util.ArrayList;

/**
 *
 * @author Nicolás
 */
public class Caretaker
{

    private final ArrayList<Memento> savedStates;

    public Caretaker()
    {
        savedStates = new ArrayList<>();
    }

    public void addMemento(Memento memento)
    {
        savedStates.add(memento);
    }

    public Memento getMemento(int idx)
    {
        return savedStates.get(idx);
    }

    public ArrayList<Memento> getSavedStates()
    {
        return savedStates;
    }

}
