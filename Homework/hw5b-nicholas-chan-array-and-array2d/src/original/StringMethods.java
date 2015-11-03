package original;
/**
 * A class to do illustrate some methods of the String Class
 * 
 * @author Richard 
 * @version 041003
 */
public class StringMethods
{
   private String message = "Default Text Message for Use with the Class";
   private String commands = "SULHQ";
   private char command = ' ';   
   public static void main(String[] args)
   {
     new StringMethods();
   }
	/**
	 * Constructor 
	 */
	public StringMethods()
	{
	  message = getMessage();
	  while (command != 'Q')
	  {
	    command = getCommand();
	    doCommand(command);
	  }
	}
	private String getMessage()
	{
	  // User types a message, if not, default is used
	  String msg = input("Please type in a text message: ");
	  if (msg.length() == 0)
	  {
	    msg = message;
	  }
	  return msg;
	}
	private char getCommand()
	{
	  String cmd = input("Please input a command (H for help on commands): ");
	  if (cmd.length() == 0)
	  {
	    return ' ';
	  }
	  else
	  {
	    return cmd.charAt(0);
	  }	  
	}
	private void doCommand(char cmd)
	{
	  // lowercase letter returns first position of that letter in the String, if any
	  // matches both lower and uppercase letters in target String
	  if ( (cmd >= 'a') && (cmd <= 'z') )
	  {
	    String testMessage = message.toLowerCase();
	    int pos = testMessage.indexOf(cmd);
	    output("That letter is found at position " + pos);
	  }
	  else
	  {
	    // check for valid command
	    if (commands.indexOf(cmd) < 0)
	    {
	      output("Unknown command: " + cmd);
	    }
	    else
	    {
	      if (cmd == 'S')
	      {
	        output(message);
	      }
	      else if (cmd == 'U')
	      {
	        message = message.toUpperCase();
	      }
	      else if (cmd == 'L')
	      {
	        message = message.toLowerCase();
	      }
	      else if (cmd == 'H')
	      {
	        output("S - show the string");
	        output("U - convert string to Uppercase");
	        output("L - convert string to Lowercase");
	        output("H - show this list");
	        output("Q - end the program");
	      }
	      else if (cmd == 'Q')
	      {
	        output("bye then");
	      }
	    }
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
