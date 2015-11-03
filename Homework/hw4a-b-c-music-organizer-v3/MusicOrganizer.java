import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * HOMEWORK TASK 4B
 *     Create a method called search(). Takes one argument: the string to search for
 *     The method returns all the songs which match the search string, or null if nothing is found.
 * 
 * HOMEOWRK TASK 4C
 *     Implement a method called search2() which uses a while loop to implement a similar search as in 4B.
 *     
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    //private MusicPlayer player;
        
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<String>();
        //player = new MusicPlayer();
    }
    
    /**
     * PART OF EXERCISE 4B
     * -Returns all the songs which match the search string, or null if no matches are found
     * -Use a for each loop to implement the search
     * 
     * @return The songs matching the search term
     * @param name The term to search for. 
     */
    public ArrayList<String> search(String name)
    {
        int numFound = 0;
        ArrayList<String> searchFiles = new ArrayList<String>();
        
        for(String filename : files) {
            if(filename.contains(name) == true)
            {
                searchFiles.add(filename);
                numFound++;
            }
        }
        
        if(searchFiles.isEmpty() == true)
        {
            searchFiles = null;
        }
        else
        {
            System.out.println("Found " + numFound + " songs:");
        }
        
        return searchFiles;
    }
   
    /**
     * PART OF EXERCISE 4C
     *     -Performs same function as search() but with a while loop
     * 
     * @return The songs matching the search term
     * @param name The term to search for. 
     */
    public ArrayList<String> search2(String name)
    {
        int numFound = 0;
        int pointer = 0;
        ArrayList<String> searchFiles = new ArrayList<String>();
        
        while(pointer > files.size()) {
            String filename = files.get(pointer);
            if(filename.contains(name) == true)
            {
                searchFiles.add(filename);
                numFound++;
            }
            pointer++;
        }
        
        if(searchFiles.isEmpty() == true)
        {
            searchFiles = null;
        }
        else
        {
            System.out.println("Found " + numFound + " songs:");
        }
        
        return searchFiles;
    }
    
    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }
    
    public String getFile(int i)
    {
        return files.get(i);
    }
    
    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }
    
    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles(ArrayList<String> listFiles)
    {
        for(String filename : listFiles) {
            System.out.println(filename);
        }
    }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            //player.startPlaying(filename);
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        //player.stop();
    }

    /**
     * Play a file in the collection. Only return once playing has finished.
     * @param index The index of the file to be played.
     */
    public void playAndWait(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            //player.playSample(filename);
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    /**
     * Edit all values in the files ArrayList by adding a string to the end of it by using a For Each loop
     * 
     * @param mod The value to add at the end of each value
     * @return The number of files edited in a string
     */
    public String editSongNameForEach(String mod)
    {
        Integer i = 0;

        for (String filename : files)
        {
            files.set(i, filename + mod);
            i++;
        }
        return i.toString();
    }

    /**
     * Edit all values in the files ArrayList by adding a string to the end of it by using a For loop
     * 
     * @param mod The value to add at the end of each value
     * @return The number of files edited in a string
     */
    public String editSongNameFor(String mod)
    {
        for (int i = 0; i < files.size(); i++)
        {
            files.set(i, files.get(i) + mod);
        }
        return files.size() + "";
    }
    
    /**
     * Edit all values in the files ArrayList by adding a string to the end of it by using a While loop
     * 
     * @param mod The value to add at the end of each value
     * @return The number of files edited in a string
     */
    public String editSongNameWhile(String mod)
    {
        int i = 0;
        
        while (i < files.size())
        {
            files.set(i, files.get(i) + mod);
            i++;
        }
        return i + "";
    }
}
 