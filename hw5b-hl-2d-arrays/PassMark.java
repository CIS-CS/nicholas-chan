// PassMark.java

package page081_Arrays;

import ibio.IBIO;

/**
 *  Stores student names in a String[].  Stores scores in an int[].
 *  If a score is less than the passing mark, the name is marked as deleted, "XXXX"
 *
 *  @author Richard
 *  @version 250903
 */
public class PassMark
{
    private final String DELETED = "XXXX";    
    private int[] scores   = new int[] { 99, 19, 56, 99, 43, 82 };    
    private String[] students =
            new String[] { "phuong", "richard", "sayaka", "jithan", "aruna",
                           "andrew" };

    public static void main(String[] args)
    {
        PassMark pm = new PassMark();
        
        pm.deleteFailures(50);
        pm.displayArrays();
    }
    
    /**
     *  "Deletes" all students whose score is lass than passingValue
     *  @param passingValue Minimum passing grade.
     */
    public void deleteFailures(int passingGrade)
    {
        for(int i = 0; i < scores.length; i++)
        {
            if (scores[i] < passingGrade)
            {
                students[i] = DELETED;
            }
        }
    }
    
    /**
     *  Display the two arrays.
     */
    public void displayArrays()
    {
        for(int i = 0; i < scores.length; i++)
        {
            IBIO.output(i + "  " + scores[i] + "  " + students[i]);
        }
    }
}


