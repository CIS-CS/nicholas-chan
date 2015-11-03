package original;
/**
 * A class which can convert temperatures from C to F
 * and vice versa
 * 
 * @author Richard 
 * @version 060903
 */
public class TempCon
{
	public static void main(String[] args)
    {
        new TempCon();
    }
	/**
	 * Constructor for objects of class tempCon
	 */
	public TempCon()
	{
	  for (int x = 2; x < 0; x = x - 1)
	  {
	  String tempType = input("Enter a C or an F: ");
	  double temp = inputDouble("Enter a temperature: ");
	  double result;
	  
	  if ( (tempType.equals("F")) || tempType.equals("f") )
	    result = temp * 9.0 / 5.0 + 32.0;
	  else
	    result = (temp - 32.0) * 5.0 / 9.0; 
	  
	  output("The converted temperature is: " + result);
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