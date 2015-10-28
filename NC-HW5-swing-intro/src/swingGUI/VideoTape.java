/*
 * VideoTape.java
 * Created on May 27, 2008, 8:28 AM
 */

package swingGUI;

/**
 * Holds information about a video tape.
 * @author Mark Hayes
 * @version Apr 2008
 */
public class VideoTape {
    
    //--------------------------------------------------- Class variables
    
    public static final int TITLE_BYTES  = 30;  // length of the video title
    public static final int LENGTH_BYTES = 4;   // int = four bytes
    public static final int LENT_BYTES   = 1;   // boolean = 1 byte
    public static final int RECORD_SIZE  = TITLE_BYTES + LENGTH_BYTES + LENT_BYTES;    

    //--------------------------------------------------- Instance variables

    private   String  title;     // title of the video
    private   int     length;    // in minutes
    private   boolean lent;      // is it lent to someone

    //--------------------------------------------------- Constructors
    /**
     *  No-argument constructor.
     */
    public VideoTape()
    {
        // Initialise instance variables to default values.
        title  = null;
        length = 0;
        lent   = false;
    }

    /**
     *  3-argument constructor.
     */
    public VideoTape(String title, int length, boolean lent)
    {
        // Initialise instance variables to specified values.
        setTitle(title);
        setLength(length);
        setLent(lent);        
    }
    
    //--------------------------------------------------- Mutators
    /**
     *  Set the title of the video tape.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     *  Set the length of the video tape.
     */
    public void setLength(int length)
    {
        this.length = length;
    }

    /**
     *  Set the borrowed status.
     */
    public void setLent(boolean lent)
    {
        this.lent = lent;
    }

    //--------------------------------------------------- Accessors
    /**
     *  Return the title of the video.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     *  Return the adjusted title of the video, so that all titles have the 
     *  same length.
     */    
    public String getAdjustedTitle()
    {
      	StringBuffer sb = new StringBuffer(title);
      	sb.setLength(TITLE_BYTES);  // pad the title field
        
      	return(sb.toString());      // convert the StringBuffer to a String
    }    

    /**
     *  Return the length of the video.
     */
    public int getLength()
    {
        return length;
    }

    /**
     *  Return the borrowed status of the video.
     */
    public boolean isLent()
    {
        return lent;
    }  
    
    //--------------------------------------------------- toString()
    /**
     *  Return a string representation of the object.
     */
    public String toString()
    {
        return (getTitle().trim() + " - " + getLength() + " - " + isLent());
    }    
}
