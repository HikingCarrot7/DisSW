package zuul;

import static java.lang.System.out;

/**
 * This class is the main class of the "World of Zuul" application. "World of Zuul" is a very simple, text based adventure game. Users can walk around some scenery. That's all. It should really be extended to make it more interesting!
 *
 * To play this game, create an instance of this class and call the "play" method.
 *
 * This main class creates and initialises all the others: it creates all rooms, creates the parser and starts the game. It also evaluates and executes the commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Game
{

    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExits(null, theatre, lab, pub);
        theatre.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside;  // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;

        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        out.println();
        out.println("Welcome to the World of Zuul!");
        out.println("World of Zuul is a new, incredibly boring adventure game.");
        out.println("Type 'help' if you need help.");
        out.println();
        out.println("You are " + currentRoom.getDescription());
        out.print("Exits: ");
        if (currentRoom.northExit != null)
            out.print("north ");
        if (currentRoom.eastExit != null)
            out.print("east ");
        if (currentRoom.southExit != null)
            out.print("south ");
        if (currentRoom.westExit != null)
            out.print("west ");
        out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if (command.isUnknown())
        {
            out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord)
        {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            default:
                break;
        }

        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information. Here we print some stupid, cryptic message and a list of the command words.
     */
    private void printHelp()
    {
        out.println("You are lost. You are alone. You wander");
        out.println("around at the university.");
        out.println();
        out.println("Your command words are:");
        out.println("   go quit help");
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if (!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if (direction.equals("north"))
            nextRoom = currentRoom.northExit;
        if (direction.equals("east"))
            nextRoom = currentRoom.eastExit;
        if (direction.equals("south"))
            nextRoom = currentRoom.southExit;
        if (direction.equals("west"))
            nextRoom = currentRoom.westExit;

        if (nextRoom == null)
            out.println("There is no door!");
        else
        {
            currentRoom = nextRoom;
            out.println("You are " + currentRoom.getDescription());
            out.print("Exits: ");
            if (currentRoom.northExit != null)
                out.print("north ");
            if (currentRoom.eastExit != null)
                out.print("east ");
            if (currentRoom.southExit != null)
                out.print("south ");
            if (currentRoom.westExit != null)
                out.print("west ");
            out.println();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            out.println("Quit what?");
            return false;
        } else
            return true; // signal that we want to quit
    }
}
