
/**
 * A heater class
 * 
 * @author Nicholas Chan 
 * @version 1.0
 */
public class Heater
{
    private int temperature; //The current temperature
    private int min; //The minimum temperature
    private int max; //The maximum temperature
    private int increment; //The amount to increment by
    
    /**
     * Constructor for objects of class Heater
     * 
     * @param min The minimum temperature that the heater will go to
     * @param max The maximum temperature that the heater will go to
     */
    public Heater(int min, int max)
    {
        temperature = 15;
        increment = 5;
        this.min = min;
        this.max = max;
    }

    /**
     * Increase the temperature by 5 degrees
     */
    public void warmer()
    {
        if (temperature + increment < max)
        {
            temperature = temperature + increment;
        }
    }
    
    
    /**
     * Decrease the temperature by 5 degrees
     */
    public void cooler()
    {
        if (temperature - increment > min)
        {
            temperature = temperature - increment;
        }
    }
    
    
    /**
     * Returns the value of the temperature
     * 
     * @return temperature The current temperature of the system
     */
    public int returnTemperature()
    {
        return temperature;
    }
    
    
    /**
     * Sets the increment value for the heater
     * 
     * @param increment The new amount to increment by
     */
    public void setIncrement(int increment)
    {
        if (increment > 0)
        {
            this.increment = increment;
        }
        else
        {
            System.out.println("Please input a valid increment");
        }
    }
}
