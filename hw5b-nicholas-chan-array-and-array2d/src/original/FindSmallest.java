package original;
/**
 * A class which finds the smallest value in an array
 * 
 * @author Richard 
 * @version 200903
 */
public class FindSmallest
{
	public static void main(String[] args)
    {
        new FindSmallest();
    }
	/**
	 * Constructor for objects of class NamesArray
	 */
	public FindSmallest()
	{
	  int[] listA = new int[] {3, 67, -9, 304, -56, 2 };
	  int pos = 1;
	  int smallest = listA[0];
	  
	  while (pos < 6)
	  {
	    if (listA[pos] < smallest)
	    {
	       smallest = listA[pos];
	    }
	    pos = pos + 1;
	  }
	  
	  output("The smallest number in the list is: " + smallest); 
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
