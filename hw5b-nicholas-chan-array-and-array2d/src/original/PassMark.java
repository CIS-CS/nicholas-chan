package original;
/**
 * The PassMArk Class, example worked algorithm from the book
 * section 2.1.4
 * 
 * @author Richard 
 * @version 250903
 */
public class PassMark
{
  static final String DELETED = "XXXX";
  static final int LAST_P = 5;
  int[] scores = new int[] {99, 19, 56, 99, 43, 82};
  String[] names = new String[] { "phuong", "richard", "sayaka",	
                                  "jithan", "aruna",   "andrew" };
  
  public static void main(String[] args)
  {
     new PassMark();
  }
  /**
   * Constructor
   */
  public PassMark()
  {
    delete( scores, 50, names );
    showArrays();
  }
  /**
   * deletes names from array in which scores < passMark
   * 
   * @param array of scores between 0 and 100
   * @param the passmark
   * @param array of names
   */
  public void delete(int[] scores, int passMark, String[] names )
  {
    for(int x = 0; x < LAST_P; x = x + 1)
    {
      if (scores[x] < passMark)
      {
        names[x] = DELETED;
      }
    }
  }
  /**
   * list the arrays on the screen
   */
  public void showArrays()
  {
    for(int x = 0; x < LAST_P; x = x + 1)
    {
      output(x + "  " + scores[x] + "  " + names[x]);
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