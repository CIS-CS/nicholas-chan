package courseOrganizer;

import java.util.ArrayList;

/**
 * Stack of strings
 * @author Nicholas
 */
public class Stack {
    ArrayList<String> stack;
    
    /**
     * Constructor for a new stack
     */
    public Stack()
    {
        stack = new ArrayList<String>();
    }
    
    /**
     * Pushes a string onto the stack
     * @param input String to add
     */
    public void push(String input)
    {
        stack.add(input);
    }
    
    /**
     * If not empty, pops an element from the stack
     * @return String at top of stack. Null if stack is empty
     */
    public String pop()
    {
        if(isEmpty() == false)
        {
            String result = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return result;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Checks if stack is empty
     * @return Returns true if stack is empty
     */
    public boolean isEmpty()
    {
        boolean condition = false;
        if(stack.size() == 0)
        {
            condition = true;
        }
        return condition;
    }
}
