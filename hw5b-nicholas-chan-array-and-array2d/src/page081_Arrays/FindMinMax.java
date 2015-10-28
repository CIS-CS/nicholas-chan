
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page081_Arrays;
import original.IBIO;

/**
 *
 * @author Nicholas
 */
public class FindMinMax {
    private int[] listA = new int[6];
    
    public static void main(String[] args)
    {
        FindMinMax fmm = new FindMinMax();
        fmm.initArray();
        IBIO.output("Largest number is " + fmm.findMax());
        IBIO.output("Smallest number is " + fmm.findMin());
    }
    
    private int findMin()
    {
        int smallest = listA[0];
        
        for(int i = 1; i < listA.length; i++)
        {
            if (listA[i] < smallest)
            {
                smallest = listA[i];
            }
        }
        
        return smallest;
    }
    
    private int findMax()
    {
        int largest = listA[0];
        
        for(int i = 1; i < listA.length; i++)
        {
            if (listA[i] > largest)
            {
                largest = listA[i];
            }
        }
        
        return largest;
    }
    
    private void initArray()
    {
        listA[0] = 3;
        listA[1] = 67;
        listA[2] = -9;
        listA[3] = 304;
        listA[4] = -109283;
        listA[5] = 2;
    }
    
}
