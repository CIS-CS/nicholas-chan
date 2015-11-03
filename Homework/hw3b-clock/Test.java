
/**
 * The Test class tests the clock.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    private ClockDisplay clock;
    
    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        createClock();
        runClock();
    }

    /**
     * Creates and sets the clock
     */
    private void createClock()
    {
        clock = new ClockDisplay(12,59,50,"12");
    }
    
    /**
     * Run the clock
     */
    private void runClock()
    {
        while(true)
        {
            clock.timeTick();
            System.out.println(clock.getTime());
        }
    }
}
