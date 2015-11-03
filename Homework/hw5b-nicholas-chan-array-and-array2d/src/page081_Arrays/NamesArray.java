package page081_Arrays;
import ibio.IBIO;

/**
 *  Prompts the user for the number of names to input, records all the inputs,
 *  and then prints all names.
 *  @author Richard 
 *  @version 060903
 */
public class NamesArray
{
    public static void main(String[] args)
    {
        int num = IBIO.inputInt("How many names would you like to enter? ");
        String[] names = new String[num];
        
        IBIO.output("\n");
        for(int i = 0; i < num; i++)
        {
            names[i] = IBIO.input("Enter a name: ");
        }        
        
        IBIO.output("The names are: ");
        for(int i = 0; i < num; i++)
        {
            IBIO.output(names[i]);
        }
    }
}
