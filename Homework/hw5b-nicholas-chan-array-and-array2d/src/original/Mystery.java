package original;
/**
 * A class which illustrates use of methods, a mystery method
 * 
 * @author Richard 
 * @version 060903
 */
public class Mystery
{
	public static void main(String[] args)
    {
        new Mystery();
    }
	/**
	 * Constructor for objects of class tempCon
	 */
	public Mystery()
	{
	  String message = input("Please type a message: ");
	  output("The result of mystery is: " + mystery(message));
	}
	
	public int mystery(String message)
	{
	  int count = 0;
	  int len = message.length();
	  for(int p = 0; p < len; p++)
	  {
	    if (message.charAt(p) == 'A')
	    {
	      count = count + 1;
	    }
	  }
	  return count;
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