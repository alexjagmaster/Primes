/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package primes;

import static java.lang.Math.sqrt;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author pbatchu
 */
public class Primes {

    /**
     * @param args the command line arguments
     */
     Deque<Integer> solutionStack = new ArrayDeque<Integer>();
         Deque<Integer>  remainingNumbersStack  = new ArrayDeque<Integer>();
         private static List<Integer> primeNumbersArray  = new ArrayList<Integer>();
    public static void main(String[] args) {
        
        populatePrimes(1, 100);     
        for (Integer num : primeNumbersArray) {
            System.out.println(num);
        }

    }
     private static void populatePrimes(int min, int max) {
        
        for (int i = min; i <= max; i++) {
             boolean isCurrNumberPrime = true;
            //optimization: need to check only till sqrt of the number instead till i-1
            for(int j=2;j<=sqrt(i);j++){
                if (i % j == 0) {
                // add to the prime number list
                isCurrNumberPrime=false;
            }
           }
            
            // if  prime
            if (isCurrNumberPrime==true) {
                // add to the prime number list
                primeNumbersArray.add(i);
            }

        }
    }
    
}
