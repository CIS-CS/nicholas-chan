import java.util.ArrayList;
import java.util.Collections;
/**
 * Creates a Music Organizer and manipulates the organizer
 * HOMEWORK TASK 4F
 *     -Create 25 songs, and play all songs in a random order
 *     
 * NOTE: WHEN playRandomSongs IS CALLED, ERRORS WILL BE RAISED
 * ABOUT NOT BEING ABLE TO PLAY THE SONG
 * THIS IS BECAUSE THE SONGS CREATED ARE BLANK, AND NOT ACTUALLY
 * LINKED TO MUSIC FILES THAT CAN BE PLAYED. 
 * 
 * @author Nicholas Chan
 */
public class Test
{
    private static MusicOrganizer organizer;
    
    public static void main(String args[])
    {
        organizer = new MusicOrganizer();
        printFiles();
        playRandomSongs();
    }
    
    /**
     * Adds the specified number of songs to the organizer
     */
    private static void addSongs(int numberOfSongs)
    {
        for (int i = 0; i < numberOfSongs; i++)
        {
            organizer.addFile("song_" + i + ".mp4");
        }
    }
    
    private static void printFiles()
    {
        organizer.listAllTracks(); 
    }
    
    private static void playRandomSongs()
    {
        addSongs(25);
        organizer.playAllRandom();
    }
}
