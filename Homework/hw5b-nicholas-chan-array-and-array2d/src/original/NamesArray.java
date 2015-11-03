package original;
/**
 * A class which uses an array
 * 
 * @author Richard 
 * @version 060903
 */
public class NamesArray
{
	public static void main(String[] args)
    {
        new NamesArray();
    }
	/**
	 * Constructor for objects of class NamesArray
	 */
	public NamesArray()
	{
	  String[] names = new String[5];
	  
	  for(int i = 0; i < 5; i = i + 1)
	  {
	    names[i] = input("please input a name: ");
	  }
	  
	  output("The second and fourth names input were: " + names[2] + ", " + names[4]); 
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
