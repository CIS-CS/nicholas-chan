package ibio;
/**
 * This class contains the IBIO algorithms (see p.47 in the text)
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IBIO {

    //============================================================
    // Below are the IBIO simple input and output methods
    // These are assumed to be copied into the source code for all
    // algorithms. A note at the end of each algorithm reminds
    // students of this fact. Students are required to
    // understand the USE of these methods, not memorize their code.
    //============================================================

    /** Output a string. */
    public static void output(String info) { System.out.println(info); }
    public static void output(char info)   { System.out.println(info); }
    public static void output(byte info)   { System.out.println(info); }
    public static void output(int info)    { System.out.println(info); }
    public static void output(long info)   { System.out.println(info); }
    public static void output(double info) { System.out.println(info); }
    public static void output(boolean info){ System.out.println(info); }

    // This method reads the keyboard. All input methods call this.
    public static String input(String prompt)
    {
        String inputLine = "";
        System.out.print(prompt);
        try
        {
            // Read from the keyboard
            inputLine = (new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in))).readLine();
        }
        catch (Exception e)
        {
            String err = e.toString();
            System.out.println(err);
            inputLine = "";
        }
        return inputLine;
    }

    // Prompt the user for anything
    public static String inputString(String prompt)
    {
        return input(prompt);
    }

    // Inut anything (without a prompt)
    public static String input()
    {
        return input("");
    }

    // Prompt the user for a character
    public static char inputChar(String prompt)
    {
        char result = (char)0;
        try
        {
            result = input(prompt).charAt(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            result = (char)0;
        }
        return result;
    }

    // Prompt the user for a byte
    public static byte inputByte(String prompt)
    {
        byte result = 0;
        try
        {
            //result = Byte.valueOf(input(prompt).trim()).byteValue();


        }
        catch (NumberFormatException e)
        {
            result = 0;
        }
        return result;
    }

    // Prompt the user for an integer
    public static int inputInt(String prompt)
    {
        int result = 0;
        try
        {
            result = Integer.valueOf(input(prompt).trim()).intValue();
        }
        catch (Exception e)
        {
            result = 0;
        }
        return result;
    }

    // Input an integer (without a prompt)
    public static int inputInt()
    {
        return inputInt("");
    }

    // Prompt the user for a long integer
    public static long inputLong(String prompt)
    {
        long result = 0;
        try
        {
            result = Long.valueOf(input(prompt).trim()).longValue();
        }
        catch (Exception e)
        {
            result = 0;
        }
        return result;
    }

    // Prompt the user for a double
    public static double inputDouble(String prompt)
    {
        double result = 0;
        try
        {
            result = Double.valueOf(input(prompt).trim()).doubleValue();
        }
        catch (Exception e)     //If input is invalid, return 0
        {
            result = 0;
        }
        return result;
    }

    // Input a double (without a prompt)
    public static double inputDouble()
    {
        return inputDouble("");
    }

    // Prompt the user for a boolean.
    public static boolean inputBoolean(String prompt)
    {
        boolean result = false;
        try
        {
            result = Boolean.valueOf(input(prompt).trim()).booleanValue();
        }
        catch (Exception e)     //If input is invalid, return false.
        {
            result = false;
        }
        return result;
    }

    //=========== end IBIO =======================================
}