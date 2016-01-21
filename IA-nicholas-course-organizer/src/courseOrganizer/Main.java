package courseOrganizer;

/**
 * Main class of the application. Initializes organizer, persistor, and GUI.
 * @author Nicholas
 */
public class Main {
    
    public static void main(String args[])
    {
        Organizer org = new Organizer();
        Persistor persist = new Persistor(org);
        GUI gui = new GUI(org, persist);
    }
}
