package original;
import java.io.*;
/**
 * A Class to keep details of VideoTapes, can read and write records
 * from an open RandomAccessFile
 * 
 * @author Richard 
 * @version 200903
 */
public class VideoTapeFile extends VideoTape
{
	// instance variables - or data members
	// These are determined by the structure of the VideoTape Class
	
	static final int TITLE_BYTES = 25;  // allowed length of video title
	static final int LENGTH_BYTES = 2;  // length is an int 16-bits/2 bytes
	static final int LENT_BYTES = 1;    // lent is boolean - 1 byte
	
	// Calculate length of a record - used to seek to correct position
	// in the data file.
	static final int RECORD_BYTES = TITLE_BYTES 
	                              + LENGTH_BYTES 
	                              + LENT_BYTES;
	/**
	 * No argument Constructor, calls VideoTape constructor
	 * super = a call to the super class constructor
	 */
	public VideoTapeFile()
	{
	    super();
	    super.title = fixLength(title, TITLE_BYTES);
	}
	/**
	 * Constructor for objects of class VideoTape
	 */
	public VideoTapeFile(String title, int length, boolean lent)
	{
	  super(title, length, lent);
	  super.title = fixLength(title, TITLE_BYTES);
	}
	/**
	 * Method to add item to data file
	 * 
	 * @param the file to add to - must be open for writing
	 * @param the object to be written into the file
	 * @param position to add the record in the file
	 * @throws IOException if unable to complete the write operation
	 */	
	
	public static void writeRecord( RandomAccessFile theFile, 
	                                VideoTapeFile theVideo, 
	                                long posToAdd ) throws IOException
	{
      theFile.seek(posToAdd);
      theFile.writeBytes(theVideo.getTitle());
      theFile.writeInt(theVideo.getLength());
      theFile.writeBoolean(theVideo.isLent());
    }
	  
	/**
	 * Method to read item from data file
	 * 
	 * @param the file to add to - must be open
	 * @param long the position to start reading in the file
	 * @return a VideoTapeFile object (null if not read)
	 * @throws IOException if unable to complete the read operation
	 */	
	public static VideoTapeFile readRecord( RandomAccessFile theFile,
	                                         long posToRead ) throws IOException
	{
	  theFile.seek(posToRead);
	  // Read a String by filling a byte array then converting
	  byte[] theTitle = new byte[TITLE_BYTES];
      theFile.readFully(theTitle);
      String title = new String(theTitle);
      // Remaining fields can be read directly
      int length = theFile.readInt();
      boolean lent = theFile.readBoolean();
      // construct and return a VideoTapeFile object
      return new VideoTapeFile(title, length, lent);
      
    }/**
	 * Method to fix length of title String
	 * 
	 * @param title - the original String
	 * @param size - the fixed length size in bytes
	 * @return a String of exactly size bytes
	 */
    private String fixLength(String title, int size)
    {
      StringBuffer sb = new StringBuffer(title);
      sb.setLength(size);
      return sb.toString();
    }
	/**
	 * Method to return record length
	 * 
	 * @return length of a VideoTapeFile object in bytes
	 */
    public static int length()
    {
      return RECORD_BYTES;
    }
    
}
