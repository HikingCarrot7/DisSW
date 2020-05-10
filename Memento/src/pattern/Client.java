package pattern;

/**
 *
 * @author Nicolás
 */
public class Client
{

    public static void main(String[] args)
    {
        Originator originator = new Originator();
        Caretaker careTaker = new Caretaker();

        originator.setActualState("Hola");
        careTaker.addMemento(originator.storeInMemento());

        originator.setActualState("Hola, esta es una prueba...");
        careTaker.addMemento(originator.storeInMemento());

        originator.setActualState("Hola, esta es una prueba... y está saliendo bien hasta el momento.");
        careTaker.addMemento(originator.storeInMemento());
    }
}
