package courseOrganizer;

/**
 * Class used for data validation
 * @author Nicholas
 */
public class DataValidate {
    /**
     * Empty constructor of the DataValidate class. 
     */
    public DataValidate(){}
    
    public String personNameError = "Name cannot contain: numbers, ~, or ;";
    public String generalNameError = "Name cannot contain: ~ or ;";
    
    /**
     * Validates data entries used for names of people. Rejects all numbers 
     *  and the two delimiter characters
     * @param input String to be verified
     * @return True for violation, false for valid input.
     */
    public boolean validatePersonName(String input)
    {
        if(input.matches("[a-zA-Z ]*\\d+.*") || input.contains(";") == true
                || input.contains("~") == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Validates data entries used for names for courses or computers. Rejects
     *  the two delimiter characters
     * @param input String to be verified
     * @return False for violation, true for valid input
     */
    public boolean validateGeneralName(String input)
    {
        if(input.contains(";") == true || input.contains("~") == true)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Validates data entries used for opening files. Checks that the file is
     * of the correct type, and is a valid file name.
     * @param input String to be verified 
     * @return True for valid, false for violation
     */
    public boolean validateOpen(String input)
    {
        if(input.contains(".reboot") && countSubString(input, ".") == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Counts the number of times a substring appears in a string. Used for 
     * some data validation applications
     * @param input Input string
     * @param search Substring to search for
     * @return Number of times the substring appears in the string
     */
    public int countSubString(String input, String search)
    {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = input.indexOf(search,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += search.length();
            }
        }
        return count;
    }
}
