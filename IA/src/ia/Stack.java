/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

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