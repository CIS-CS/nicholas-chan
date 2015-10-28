package original;
/**
 * A class to do limited tokenisation, not a substitute for Java's
 * StringTokenizer Class!
 * 
 * @author Richard 
 * @version 061003
 */
public class Tokenizer
{
    private static final int MAX = 6;             // maximum tokens in a string
    private static final int LONGEST_TOKEN = 20;  // length of longest single token
    private static final char SPACE = ' ';        // token separator
    
    // StringBuffer and StringBuffer array to hold initial message and data
    // Class could be adapted to tokenise String input by user to investigate limitations
    // of this example by a suitable set of test data
    private StringBuffer[] data = new StringBuffer[MAX];  // new array of StringBuffer references
    private StringBuffer message = new StringBuffer("Henry the Human Fly");
 
	 public static void main(String[] args)
    {
        new Tokenizer();
    }
	/**
	 * Constructor 
	 */
	public Tokenizer()
	{
	  tokenize(message);
	  printWords();
	}
	private void tokenize(StringBuffer message)
	{
	  int pos = 0;                              // position in the String
	  int count = 0;                            // element in data array 
	  int len = message.length();               // length of initial String
	  
	  // loop through message until end or no more space for tokens
	  while ( (pos < len) && (count < MAX) )
	  {
	    // Original array just a set of empty object references, so construct new instance
	    // for each array element
	    data[count] = new StringBuffer(LONGEST_TOKEN);
	    
	    // find end of current token, marked by a space
	    while ( (pos < len) && (message.charAt(pos)!=(SPACE)) )
	    {
	      data[count].append(message.charAt(pos));
	      pos = pos + 1; 
	    }
	    count = count + 1;
	    pos = pos + 1;
	  }
	}
	private void printWords()
	{
	  for(int x = 0; x < MAX; x = x + 1)
	  {
	    output(x + " - " + data[x]);
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
