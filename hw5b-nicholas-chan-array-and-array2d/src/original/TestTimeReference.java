package original;
/**
 * A class to test object references
 * 
 * @author Richard 
 * @version 070903
 */
public class TestTimeReference
{
	 public static void main(String[] args)
    {
        new TestTimeReference();
    }
	/**
	 * Constructor 
	 */
	public TestTimeReference()
	{
	  String sHour = input("Please input the hour (0-11): ");
	  String indicator = input("Please indicate am or pm (a, p): ");
	  try
	  {
	    // set up time1 with the new time
	    Time time1 = new Time(sHour, indicator);
	    output("The time is " + time1.toString());
	    // Create a new time reference (not an Object!)
	    Time time2 = time1;
	    // Change time2 (and therefore time1)
	    time2.setTimeMidnight();
	    output("The new time is " + time1.toString());	 
	    // NB I had to change the Time Class to make setTimeMidnight()
	    // a public method!
	  }
	  catch( TimeClassException e )
	  {
	    output("Something wrong " + e.getMessage()); 
	  }
	}
	
	/**
	 * IBIO methods, (c) International Baccalaureate 2003
	 * Computer Science Subject Guide, Appendix 2.
	 */
	
	 static void output(String info) {  System.out.println(info); }
     static void output(double info) {  System.out.println(info); }
     static void output(int info)    {  System.out.println(info); }
  
     static int inputInt(String Prompt)
     {  
         int result=0;
         try{result=Integer.parseInt(input(Prompt).trim());}
         catch (Exception e){result = 0;}
         return result;
     }
     static double inputDouble(String Prompt)
     {  
         double result=0;
         try{result=Double.valueOf(input(Prompt).trim()).doubleValue();}
         catch (Exception e){result = 0;}
         return result;
     }
     static String input(String prompt)
     {  
         String inputLine = "";
         System.out.print(prompt);
         try
         { 
             java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
             java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
             inputLine = inBuffer.readLine();
         }
         catch (Exception e)
         { 
             String err = e.toString();
             System.out.println(err);
         }
         return inputLine;
      }
      static String input() { return input("");  }
}
