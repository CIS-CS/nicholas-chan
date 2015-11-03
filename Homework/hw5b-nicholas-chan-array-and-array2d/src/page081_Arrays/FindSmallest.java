package page081_Arrays;
import original.IBIO;

/**
 *  Finds the largest value in an array of int's
 *  @author Nicholas
 *  @version 201501
 */
public class FindSmallest
{
    public static void main(String[] args)
    {
        // The tedious way to initialize the array
        int[] listA = new int[6];
        listA[0] = 3;
        listA[1] = 67;
        listA[2] = -9;
        listA[3] = 304;
        listA[4] = -109283;
        listA[5] = 2;

        // A simpler way to initialize the array
        int[] listB = { 3, 67, -9, 304, -56, 2 };
        
        int index = 1;
        int smallest = listA[0];        // Assume the first one is the smallest
        
        while (index < listA.length)
        {
            if (listA[index] < smallest)
            {
                smallest = listA[index];
            }
            index = index + 1;
        }        
        IBIO.output("\nThe smallest number in the list is: " + smallest);
    }
}






