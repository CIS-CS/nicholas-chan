/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
package page081_Arrays;

public class MultiDimArrayDemo {
                                                                                
    public static void main(String[] args){
          
        String[][] names = { {"Mr. ","Mrs. ","Ms. "},
                             {"Smith","Jones"}
                           };
        System.out.println(names[0][0] + names[1][0]);    // Mr. Smith
        System.out.println(names[0][2] + names[1][1]);    // Ms. Jones
        
        // Display the length of each "row" in the 2D ar
        for (int i = 0; i < names.length; i++)
        {
            System.out.println(names[i].length);
        }
        
        // Create a 10x10 array of ints
        int[][] test = new int[10][10];

        // Loop 1
        // Changes the "dimensions" of the array from a square to a ?
        for (int i = 0; i < 10; i++)
        {
            test[i] = new int[i+1];
        }

        // Initialize all elements of the jagged array to 99
        for (int i = 0; i < test.length; i++)
        {
            for (int j = 0; j < test[i].length; j++)
            {
                test[i][j] = 99;
            }
        }          
    }
}










