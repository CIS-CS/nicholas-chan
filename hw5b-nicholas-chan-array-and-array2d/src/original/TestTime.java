package original;
/**
 * A class to test the Time Class
 * 
 * @author Richard 
 * @version 070903
 */
public class TestTime
{
    public static void main(String[] args)
    {
        new TestTime();
    }
    
    /**
     * Constructor 
     */
    public TestTime()
    {
        String sHour = IBIO.input("Please input the hour (0-11): ");
        String indicator = IBIO.input("Please indicate am or pm (a, p): ");
        try
        {
            Time now = new Time(sHour, indicator);
            IBIO.output("The time is " + now.toString());
        }
        catch( TimeClassException e )
        {
            IBIO.output("Something wrong " + e.getMessage()); 
        }
    }
}
