package ejemplo;

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

        originator.setActualState("Hola, esta es una prueba... y parece que está saliendo bien.");
        careTaker.addMemento(originator.storeInMemento());

        System.out.println("El estado del escrito en el momento 1 es: " + careTaker.getMemento(0).getSavedState());
        System.out.println("El estado del escrito en el momento 2 es: " + careTaker.getMemento(1).getSavedState());
        System.out.println("El estado del escrito en el momento 3 es: " + careTaker.getMemento(2).getSavedState());
    }
}
