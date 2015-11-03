/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicholas
 */
public class BaseConverter {

    public BaseConverter() {
        //System.out.println(convertNum(2,10,2));
        
    }
    
    public String convertNum(int input, int oriBase, int desBase) {
        return Integer.toString(
                Integer.parseInt(Integer.toString(input), oriBase), desBase);
    }
}
