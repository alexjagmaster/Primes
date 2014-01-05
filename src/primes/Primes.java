/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primes;

import static java.lang.Math.sqrt;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
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
    private static int max = 8;
    private static Deque<Integer> solutionStack = new ArrayDeque<Integer>(max);
    private static Deque<Integer> remainingNumbersStack = new ArrayDeque<Integer>(max);
    private static List<Integer> primeNumbersArray = new ArrayList<Integer>(max);
    private static List<Integer> listOfEleThatHaveBeenRecycled = new ArrayList<Integer>();
    private static int currSizeOfSolutionArray = 0;
    private static int prevSizeOfSolutionArray = 0;
    private static int stuckCounter = 0;
    private static boolean infiniteLoopDetected = false;
    
   private static List<Integer> solutionArray = new ArrayList<Integer>(max);
      private static          List<Integer> unresolvedArray = new ArrayList<Integer>();

    public static void main(String[] args) {

        populatePrimes(min, max);
        //***System.out.println("Populated primes are: ");
        printPrimes();
        //food for thought
        listOfEleThatHaveBeenRecycled.add(-1);

        //Display Primes
//        for (Integer num : primeNumbersArray) {
//            //***System.out.println(num);
//        }
        //populating the first element of the solutionArray manually
        solutionStack.push(min);
        //populating the remainingnumber stack (min+1 to max as min is already pushed to solutionArray)
        for (int i = min + 1; i <= max; i++) {
            remainingNumbersStack.push(i);
        }
        //***System.out.println("Beginning SolutionStack is: ");
//        printSolution();
        calculateSolution();
//        System.out.println("Solution: ");
//        printSolution();
//        System.out.println("");
//        System.out.println("Unresolved elements: sdf");
        printRemainingEleStack();
        
//        System.out.println("Solution: ");
        printSolutionArray();

    }

    private static void calculateSolution() {

        if (remainingNumbersStack.size() == 1) {
            solutionStack.push(remainingNumbersStack.pop());
            return;
        }
        //***System.out.println("solutionStack.getFirst() is "+solutionStack.getFirst());
        //***System.out.println("remainingNumbersStack.getFirst() is "+remainingNumbersStack.getFirst());
        int sumOfHeadsOfSolAndRemaining = solutionStack.getFirst() + remainingNumbersStack.getFirst();
        //check if sum of the last element of SA and the top most element of RS is a prime number (if its in the primeNumberArray)
        if (primeNumbersArray.contains(sumOfHeadsOfSolAndRemaining)) {
            //If it's a prime, it's eligible to be pushed to the solutionstack and be placed next to the current element. 
            solutionStack.push(remainingNumbersStack.getFirst());
            //***System.out.println("Current state of SolutionStack: ");
//            printSolution();

            //Now popping out the last element of the remainingstackarray as its already pushed to the solution array
            remainingNumbersStack.pop();
//             if (solutionStack.size() > max) {
//                return;
//            }

        } else {
            currSizeOfSolutionArray = solutionStack.size();
            if (prevSizeOfSolutionArray == currSizeOfSolutionArray) {
                stuckCounter++;
            } else if (prevSizeOfSolutionArray < currSizeOfSolutionArray) {
                stuckCounter = 0;
            }

            if (stuckCounter > max) {
//                reverseSolutionStack();
                if (infiniteLoopDetected) {
                    System.out.println("stopping! loop detected after " + stuckCounter + " attempts!");
                    return;
                }
                infiniteLoopDetected = true;

                

           //copying over the remnants to Arrays. Sunk cost!!!
                for (int currEle : remainingNumbersStack) {
                    unresolvedArray.add(currEle);
                }
                for (int currEle1 : solutionStack) {
                    solutionArray.add(currEle1);
                }

                
                printSolutionArray();
                printRemainingEleStack();
               
                System.out.println("Unresolved elements remain. Trying out a secondary Algorithm. Here is the final solution");
int targetSum=0;
int secondtargetSum=0;
                for (int i = 0; i < unresolvedArray.size(); i++) {
                  //looping through solutionArray
                    for(int j=0;j<solutionArray.size();j++){
                        //if the index is 0 then only the 0th ele can be compared and if successful, the unresolvedarray ele can be placed before it
                          if (j== 0) {
                              targetSum=unresolvedArray.get(i)+solutionArray.get(j);
                              if(primeNumbersArray.contains(targetSum)){
                                  solutionArray.add(0, unresolvedArray.get(i));
                                  remainingNumbersStack.removeFirstOccurrence(unresolvedArray.get(i));
                                  
                              }

                    } else 
                              //if the index is of the last ele, and match is successful, the unresolved.. can be placed after it
                              if (j == solutionArray.size() - 1) {
                        targetSum=unresolvedArray.get(i)+solutionArray.get(j);
                            if(primeNumbersArray.contains(targetSum)){
                                  solutionArray.add(unresolvedArray.get(i));
                                  remainingNumbersStack.removeFirstOccurrence(unresolvedArray.get(i));
                                  
                              }

                    } else {
                                  targetSum=unresolvedArray.get(i)+solutionArray.get(j);
                                  secondtargetSum=unresolvedArray.get(i)+solutionArray.get(j+1);
                            if(primeNumbersArray.contains(targetSum) &&
                                      primeNumbersArray.contains(secondtargetSum)){
                                 solutionArray.add(j+1, unresolvedArray.get(i));
                                 remainingNumbersStack.removeFirstOccurrence(unresolvedArray.get(i));
                                
                            }
                    }
                    }
                    
                }
return;
            }
            if (remainingNumbersStack.size() == 1) {
                return;
            }
            if (listOfEleThatHaveBeenRecycled.contains(remainingNumbersStack.getFirst())) {
//                solutionStack = Collections.reverse(solutionStack.clone());
//                reverseSolutionStack();

            }

            //pushing the top element to the end of the stack
            int tempSwapHolderHead = remainingNumbersStack.getFirst();
            if (!(remainingNumbersStack.isEmpty())) {
                //***System.out.println("Current state of remainingEleStack just before popping: ");
                //printRemainingEleStack();

            }

            remainingNumbersStack.offerLast(tempSwapHolderHead);
            //marking this element as touched
//            listOfEleThatHaveBeenRecycled.add(tempSwapHolderHead);
            if (remainingNumbersStack.size() >= 1) {
                remainingNumbersStack.pop();
            }
//           int tempSwapHolderHeadMinusOne=remainingNumbersStack.getFirst();
//           remainingNumbersStack.pop();
//           remainingNumbersStack.push(tempSwapHolderHead);
//           remainingNumbersStack.push(tempSwapHolderHeadMinusOne);
            prevSizeOfSolutionArray = solutionStack.size();

        }
//        System.out.println("State of SolutionStack after the current interation: ");
//            printSolution();

        calculateSolution();

    }

    private static void populatePrimes(int min, int max) {

        for (int i = min; i <= max * 2; i++) {
            boolean isCurrNumberPrime = true;
            //optimization: need to check only till sqrt of the number instead till i-1
            for (int j = 2; j <= sqrt(i); j++) {
                if (i % j == 0) {
                    // add to the prime number list
                    isCurrNumberPrime = false;
                }
            }

            // if  prime
            if (isCurrNumberPrime == true) {
                // add to the prime number list
                primeNumbersArray.add(i);
            }

        }
    }

    private static void printSolution() {
        for (int i : solutionStack) {
            System.out.print(i + " ");
        }
        //***System.out.println();
    }

    //should've used generics.. Just being lazy
    private static void printRemainingEleStack() {
         System.out.println("unresolved elements");
        for (int i : remainingNumbersStack) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void printSolutionArray() {
                System.out.println("solutionArray");
                for (int currEle : solutionArray) {
                    System.out.print(currEle + " ");
                }
                System.out.println();
    }

    //should've used generics.. Just being lazy
    private static void printRemainingEleArray() {
         System.out.println("unresolvedArray");
                for (int currEle : unresolvedArray) {
                    System.out.print(currEle + " ");
                }

    }

    private static void printPrimes() {
        for (int i : primeNumbersArray) {
            //***System.out.print(i + " ");
        }
        //***System.out.println();
    }

    public static void reverseSolutionStack() {
        Deque<Integer> reversedSolutionStack = new ArrayDeque<Integer>(max);
        Iterator it = solutionStack.descendingIterator();
        while (it.hasNext()) {
            int element = (int) it.next();
            reversedSolutionStack.addLast(element);
        }
        solutionStack = reversedSolutionStack;
    }

    public static boolean checkFit(int unresolvedEle) {

        return false;
    }

}
