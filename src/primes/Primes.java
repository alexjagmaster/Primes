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
        System.out.println("Populated primes are: ");
        printPrimes();
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
        calculateSolution();

    }

    private static void calculateSolution(){
        
        if(remainingNumbersStack.size()<1)
            return;
        int sumOfHeadsOfSolAndRemaining=solutionStack.getFirst()+remainingNumbersStack.getFirst();
              //check if sum of the last element of SA and the top most element of RS is a prime number (if its in the primeNumberArray)
       if(primeNumbersArray.contains(sumOfHeadsOfSolAndRemaining)){
           //If it's a prime, it's eligible to be pushed to the solutionstack and be placed next to the current element. 
           solutionStack.push(remainingNumbersStack.getFirst());
           System.out.println("Current state of SolutionStack: ");
           printSolution();
           if(solutionStack.size()>max)
               return;
           //Now popping out the last element of the remainingstackarray as its already pushed to the solution array
           remainingNumbersStack.pop();
           
       }
       else{
           //pushing the top element to the end of the stack
           int tempSwapHolderHead=remainingNumbersStack.getFirst();
           if(!(remainingNumbersStack.isEmpty()))
               System.out.println("Current state of remainingEleStack just before popping: ");
               printRemainingEleStack();
           remainingNumbersStack.pop();
           remainingNumbersStack.offerLast(tempSwapHolderHead);
//           int tempSwapHolderHeadMinusOne=remainingNumbersStack.getFirst();
//           remainingNumbersStack.pop();
//           remainingNumbersStack.push(tempSwapHolderHead);
//           remainingNumbersStack.push(tempSwapHolderHeadMinusOne);
           
       }
       calculateSolution();
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
     private static void printSolution(){
            for(int i:solutionStack)
       System.out.print(i+ " ");
            System.out.println();
    }
     //should've used generics.. Just being lazy
      private static void printRemainingEleStack(){
            for(int i:remainingNumbersStack)
       System.out.print(i+ " ");
            System.out.println();
    }
     
      private static void printPrimes(){
            for(int i:primeNumbersArray)
       System.out.print(i+ " ");
            System.out.println();
    }
    
}
