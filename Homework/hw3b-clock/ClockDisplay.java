
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 * 
 * MODIFICATIONS: 
 * -Changed both Constructors to include a "mode" parameter to change 
 *  between 12 hour and 24 hour time.
 * -Modified "updateDisplay" method to include a mode for
 *  12 hour and 24 hour time.
 * -Added "convertHours" method to convert 24 hour values to 12 hour values
 * -Added "ampm" and "mode" strings to accomodate 12 hour time display.
 */
public class ClockDisplay
{
    private NumberDisplay hours;        
    private NumberDisplay minutes;  
    private NumberDisplay seconds;  
    private String displayString;    // simulates the actual display
    private String ampm;
    private String mode;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay(String mode)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        this.mode = mode;
        updateDisplay(mode);
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, int second, String mode)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute, second);
        this.mode = mode;
        updateDisplay(mode);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        seconds.increment();
        if(seconds.getValue() == 0)
        {
            minutes.increment();
            if(minutes.getValue() == 0) {  // it just rolled over!
                hours.increment();
            }
        }
        updateDisplay(mode);
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, int second)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay(mode);
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     * 
     * MODIFICATIONS: Added a mode parameter that allows the clock to be changed
     * between a 12 hour and a 24 hour clock.
     */
    private void updateDisplay(String mode)
    {
        if ( mode == "24" ) //If the mode is 24 hour clock
        {
            displayString = hours.getDisplayValue() + ":" 
                + minutes.getDisplayValue() + ":"
                + seconds.getDisplayValue();
        }
        if ( mode == "12" ) //If the mode is 12 hour clock
        {
            displayString = convertHours() + ":" 
                + minutes.getDisplayValue() + ":"
                + seconds.getDisplayValue()
                + ampm;
        }
    }
    
    /**
     * MODIFICATION: Convrts the 24 hour time to a 12 hour time
     */
    private int convertHours()
    {
        int hoursTwelve; //Temporary int
        
        if ( hours.getValue() >= 12 ) //If the 24 hour clock is above or equal to 13 
        {
            if( hours.getValue() == 12 )
            {
                hoursTwelve = hours.getValue();
            }
            else
            {
                hoursTwelve = hours.getValue() - 12;
            }
            ampm = " PM"; //Set a string for the AM / PM valu
        }
        else
        {
            if( hours.getValue() == 0 )
            {
                hoursTwelve = hours.getValue() + 12;
            }
            else
            {
                hoursTwelve = hours.getValue();
            }
            ampm = " AM";
        }
        return hoursTwelve;
    }
}
