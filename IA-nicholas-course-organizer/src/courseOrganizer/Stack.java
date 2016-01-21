package courseOrganizer;

import java.util.ArrayList;

/**
 * Stack of strings
 * @author Nicholas
 */
public class Stack {
    ArrayList<String> stack;
    
    public Stack()
    {
        stack = new ArrayList<String>();
    }
    
    public void push(String input)
    {
        stack.add(input);
    }
    
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
