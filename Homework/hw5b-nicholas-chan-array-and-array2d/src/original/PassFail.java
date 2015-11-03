package original;
/**
 * The PassFail Class, example worked algorithm from the book
 * 
 * @author Richard 
 * @version 060903
 */
public class PassFail
{
	public static void main(String[] args)
    {
        new PassFail();
    }
	/**
	 * Constructor for objects of class tempCon
	 */
	public PassFail()
	{
      int mark1 = inputInt("Please input the first mark: "); 
      int mark2 = inputInt("Please input the first mark: ");

      double m1 = mark1 * 0.75;
      double m2 = mark2 * 0.25;
      int total = (int) (mark1 + mark2);

      if (total >= 45)
        output("You passed"); 
      else
        output("You failed");
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