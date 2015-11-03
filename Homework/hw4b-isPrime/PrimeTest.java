import java.util.*;
/**
 * Tests if numbers are prime numbers
 * 
 * @author Nicholas Chan
 * @version 07 Oct 2014
 */
public class PrimeTest
{
    
    public PrimeTest()
    {
        
    }
    
    /**
     * Checks if a number is prime
     * @param number The number to test
     * @return isPrime  A boolean showing if the number is prime.
     */
    public boolean isPrime(int number)
    {
        int testNum = number - 1;
        boolean isPrime = true;
        while(testNum >= 2)
        {
            if(number % testNum == 0)
            {
                isPrime = false;
            }
            testNum--;
        }
        return(isPrime);
    }
    
    /**
     * Returns the prime numbers before the input number to be tested
     * while being stored in an array
     * 
     * NOTE: METHOD NOT REQUIRED IN EXERCISE 4, ONLY FOR EXPERIMENTATION
     * 
     * @param number    The number to be tested
     */
    public void showPrimesBeforeArray(int number)
    {
        int[] primeArray;
        primeArray = new int[100];
        int primeIndex = 0;
        int testNum = number-1;
        System.out.println("Testing for primes before " + number);
        while(testNum >= 2)
        {
            if(isPrime(testNum))
            {
                primeArray[primeIndex] = testNum;
                primeIndex++;
            }
            testNum--;
        }
        
        while(primeIndex >= 0)
        {
            System.out.print(primeArray[primeIndex] + ", ");
            primeIndex--;
        }
    }

    /**
     * Returns the prime numbers before the input number to be tested
     * while being stored in an arrayList
     * 
     * NOTE: METHOD NOT REQUIRED IN EXERCISE 4, ONLY FOR EXPERIMENTATION
     * 
     * @param number    The number to be tested
     */
    public void showPrimesBeforeArrayList(int number)
    {
        ArrayList primeArray = new ArrayList();
        int primeIndex = 0;
        int testNum = number-1;
        System.out.println("Testing for primes before " + number);
        while(testNum >= 2)
        {
            if(isPrime(testNum))
            {
                primeArray.add(testNum);
                primeIndex++;
            }
            testNum--;
        }
        
        while(primeIndex >= 0)
        {
            System.out.print(primeArray.get(primeIndex) + ", ");
            primeIndex--;
        }
    }
    
    /**
     * Returns the prime numbers before the input number with 
     * terminal results
     * @param number    The number to be tested
     */
    public void showPrimesBefore(int number)
    {
        int testNum = number-1;
        System.out.println("Testing for primes before " + number);
        while(testNum >= 2)
        {
            if(isPrime(testNum) == true)
            {
                System.out.println(testNum);
            }
            testNum--;
        }
    }
}