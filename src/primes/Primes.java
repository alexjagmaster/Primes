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
     private static int min = 1;
         private static int max =7;
       private static Deque<Integer> solutionStack = new ArrayDeque<Integer>(max);
          private static Deque<Integer>  remainingNumbersStack  = new ArrayDeque<Integer>(max);
         private static List<Integer> primeNumbersArray  = new ArrayList<Integer>(max);
        
    public static void main(String[] args) {
        
        populatePrimes(min, max); 
        //Display Primes
//        for (Integer num : primeNumbersArray) {
//            System.out.println(num);
//        }
        //populating the first element of the solutionArray manually
        solutionStack.push(min);
        //populating the remainingnumber stack (min+1 to max as min is already pushed to solutionArray)
        for(int i=min+1;i<=max;i++)
        {
            remainingNumbersStack.push(i);
        }
        calculateSolution(remainingNumbersStack);

               
   for(int i:solutionStack)
       System.out.println(i);
    }
    private static void calculateSolution(Deque<Integer> remainingNumbersStack){
        
        if(remainingNumbersStack.size()<1)
            return;
        int sumOfHeadsOfSolAndRemaining=solutionStack.getFirst()+remainingNumbersStack.getFirst();
              //check if sum of the last element of SA and the top most element of RS is a prime number (if its in the primeNumberArray)
       if(primeNumbersArray.contains(sumOfHeadsOfSolAndRemaining)){
           //If it's a prime, it's eligible to be pushed to the solutionstack and be placed next to the current element. 
           solutionStack.push(remainingNumbersStack.getFirst());
           if(solutionStack.size()>max)
               return;
           //Now popping out the last element of the remainingstackarray as its already pushed to the solution array
           remainingNumbersStack.pop();
           
       }
       else{
           //pushing the top element to the end of the stack
           int tempSwapHolderHead=remainingNumbersStack.getFirst();
           if(!(remainingNumbersStack.isEmpty()))
           remainingNumbersStack.pop();
           remainingNumbersStack.offerLast(tempSwapHolderHead);
//           int tempSwapHolderHeadMinusOne=remainingNumbersStack.getFirst();
//           remainingNumbersStack.pop();
//           remainingNumbersStack.push(tempSwapHolderHead);
//           remainingNumbersStack.push(tempSwapHolderHeadMinusOne);
           
       }
       calculateSolution(remainingNumbersStack);
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
