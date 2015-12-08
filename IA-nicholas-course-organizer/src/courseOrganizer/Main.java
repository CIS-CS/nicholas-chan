/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseOrganizer;


/**
 *
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
