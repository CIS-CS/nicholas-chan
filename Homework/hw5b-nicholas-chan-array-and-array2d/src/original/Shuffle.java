package original;
/**
 * The Shuffle Class, example worked algorithm from the book
 * section 2.1.5
 * 
 * @author Richard 
 * @version 021003
 */
public class Shuffle
{
  static final String MARKER = "XXXX";
  static final int LAST_P = 5;
  String[] names = new String[] { MARKER, MARKER, "sayaka",	
                                  "jithan", MARKER,   "andrew" };
  
  public static void main(String[] args)
  {
     new Shuffle();
  }
  /**
   * Constructor
   */
  public Shuffle()
  {
    shuffle();
    showArray();
  }
  /**
   * shuffles names not matching special marker to front of array
   * 
   */
  public void shuffle()
  {
    int i, j; // pointers to array names
    i = 0;
    j = 0;
    
    // loop until end of array is reached
    
    while (j <= LAST_P)
    {
      // Advance pointers to first XXXX entry
      if (!names[i].equals(MARKER))
      {
        i = i + 1;
        j = j + 1;
      }
      else
      {
      // advance pointer to first non-XXXX entry
        while(names[j].equals(MARKER))
        {
          j = j + 1;
        }
        // place valid entry in XXXX position
        // replace entry with XXXX
        names[i] = names[j];
        names[j] = MARKER;
      }
      // advance the pointers
      i = i + 1;
      j = j + 1; 
    }  
  }
  /**
   * list the names array on the screen
   */
  public void showArray()
  {
    for(int x = 0; x < LAST_P; x = x + 1)
    {
      output(x + "  " + names[x]);
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