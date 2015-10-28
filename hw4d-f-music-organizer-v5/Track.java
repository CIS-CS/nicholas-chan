/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // Length of the Song
    private String length;
    // Year of song
    private String year;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename, String length, String year)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.length = length;
        this.year = year;
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        this("unknown", "unknown", filename, "unknown", "unknown");
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
        
    /**
     * Return the length.
     * @return The length.
     */
    public String getLength()
    {
        return length;
    }
    
        /**
     * Return the year.
     * @return The year.
     */
    public String getYear()
    {
        return year;
    }
    
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + ":" + length + ":" + year + 
            "  (file: " + filename + ")";
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, 
                            String filename, String length, 
                            String year)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.length = length;
        this.year = year;
    }
    
}
