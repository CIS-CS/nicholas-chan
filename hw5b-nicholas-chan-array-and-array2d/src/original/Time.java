package original;
/**
 * Class Time is used to store a time as an hour of the day
 * 
 * @author Richard
 * @version 070903
 */
public class Time
{
    // Properties of our Time Class
    private String sHour;     // hour as a String
    private int iHour;        // hour as an int
    private char indicator;   // indicator for am/pm

    /**
     * Constructor for objects of class Time
     */
    public Time()
    {
        // initialise instance variables to midnight
        setTimeMidnight();
    }
	
    public Time(String hr, String id) throws TimeClassException
    {
        // convert the hour string to an int, trim spaces of id and lower case
        // then check the range of both
        int ih = Integer.parseInt(hr.trim());
        
        id.toLowerCase().trim();
        
        if ( (ih < 0) || (ih > 11) && !((id.equals("am")) || (id.equals("pm"))) )
        {
            // invalid time, set time to midnight
            setTimeMidnight();
            throw new TimeClassException("Error, invalid time");
        }
        else
        // everything OK, set the time values
        {
            sHour = hr;
            iHour = ih;
            indicator = id.charAt(0);
        }
    }
        
    public void setTimeMidnight()
    {
        sHour = "00";
        iHour = 0;
        indicator = 'a';	  
    }
        
    /**
     * Method to return the time as a String
     * 
     * @return the time as a String 
     */
    public String toString()
    {
        String s_indicator;
        
        if (indicator == 'a')
        {
            s_indicator = "AM";
        }
        else
        {
            s_indicator = "PM";
        }
        return (sHour + " " + s_indicator);
    }
}
