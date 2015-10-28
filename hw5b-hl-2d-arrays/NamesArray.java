package page081_Arrays;
import ibio.IBIO;

/**
 *  Prompts the user for five names, stores the name in an array, displays the 
 *  third and fifth name from the array.
 *  @author Richard 
 *  @version 060903
 */
public class NamesArray
{
    public static void main(String[] args)
    {
        String[] names = new String[5];
        
        IBIO.output("\n");
        for(int i = 0; i < 5; i = i + 1)
        {
            names[i] = IBIO.input("Enter a name: ");
        }        
        IBIO.output("The 3rd and 5th names were: " + names[2] + " and  " + names[4]);
    }
}
