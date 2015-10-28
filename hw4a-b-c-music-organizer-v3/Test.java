import java.util.ArrayList;
/**
 * Creates a Music Organizer and manipulates the organizer
 * HOMEWORK TASK 4A: 
 *     Create a class called Test
 *     Add a method called addSongs() which adds songs to the organizer.
 *         The method should take the number of songs to add
 *     Add 100 songs to the organizer
 * 
 * HOMEWORK TASK 4B:
 *     In the test method, add 200 songs to the organizer, then invoke
 *     the search method with some string. In the BlueJ terminal window, 
 *     display the result, or "No songs found."
 * @Nicholas Chan
 * @1.0()
 */
public class Test
{
    private static MusicOrganizer organizer;
    
    /**
     * Creates the music organizer, adds the specified number of songs, and then prints all songs.
     */
    public static void main(String args[])
    {
        organizer = new MusicOrganizer();
        addSongs(Integer.parseInt(args[0]));
        printFiles();
    }
    
    /**
     * PART OF EXERCISE 4A
     * -Adds the number of songs. 
     * 
     * @param numberOfSongs The number of songs to be added to the music organizer
     */
    public static void addSongs(int numberOfSongs)
    {
        for (int i = 0; i < numberOfSongs; i++)
        {
            organizer.addFile("song_" + i + ".mp4");
        }
    }
    
    /**
     * PART OF EXERCISE 4B
     * -Add 200 songs to the organizer
     * -Searches for songs matching the search term
     * 
     * @param search The string to search for. 
     */
    public static void test(String search)
    {
        organizer = new MusicOrganizer();
        addSongs(200);
        ArrayList<String> searchItems = new ArrayList<String>();
        searchItems = organizer.search2(search);
        organizer.listAllFiles(searchItems);
    }
    
    /**
     * Prints all files in the organizer. 
     */
    public static void printFiles()
    {
        for (int i = organizer.getNumberOfFiles() - 1; i >= 0; i--)
        {
            System.out.println(organizer.getFile(i));    
        } 
    }
}
