/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Gurmandeep
 */


 class Euler {

        public static int[] primeList;

        public static void main(String[] args) {                        
            primeList = ESieve(100000);

            int targetpf = 4;
            int targetConsec = 4;
            int consec = 1;
            int result = 2 * 3 * 5 * 7;

            while (consec < targetConsec) {
                result++;
                if (NumberOfPrimeFacors(result) >= targetpf) {
                    consec++;
                } else {
                    consec = 0;
                }
            }                           
            System.out.println("The first of the {0} consecutive integers to have {1} distinct prime factors is {2}"+" "+targetConsec+" "+targetpf+" "+(result-targetConsec+1)+","+(result-targetConsec+2)+","+(result-targetConsec+3)+","+(result-targetConsec+4));
        }            

       /* public static void BruteForce() {
            primeList = ESieve(100000);

            int targetpf = 4;
            int targetConsec = 4;
            int consec = 1;
            int result = 2 * 3 * 5 * 7;

            while (consec < targetConsec) {
                result++;

                if (NumberOfPrimeFacors(result) >= targetpf) {
                    consec++;
                } else {
                    consec = 0;
                }
            }                           
            System.out.println("The first of the {0} consecutive integers to have {1} distinct prime factors is {2}"+" "+targetConsec+" "+targetpf+" "+(result-targetConsec+1)+","+(result-targetConsec+2)+","+(result-targetConsec+3)+","+(result-targetConsec+4));
        } */
        
        private static int NumberOfPrimeFacors(int number) {
            int nod = 0;
            boolean pf;
            int remain = number;

            for (int i = 0; i < primeList.length; i++) {

                // In case there is a remainder this is a prime factor as well
                // The exponent of that factor is 1
                if (primeList[i] * primeList[i] > number) {
                    return ++nod;
                }

                pf = false;
                while (primeList[i]!=0) {
                    if(remain % primeList[i] == 0) {
                        pf = true;
                        remain = remain / primeList[i]; 
                    } else  {
                        break;
                    }
                }
                if (pf){
                    nod++;
                }
                
                //If there is no remainder, return the count
                if (remain == 1) {
                    return nod;
                }
            }
            return nod;
        }

        // Returns the list of prime numbers up to the input
        public static int[] ESieve(int upperLimit) {

            int sieveBound = (int)(upperLimit - 1) / 2;
            int upperSqrt = ((int)Math.sqrt(upperLimit) - 1) / 2;

            //Boolean PrimeBits = new Boolean(sieveBound + 1, true);
            boolean[] PrimeBits = new boolean[sieveBound + 1];
            for(int i = 0; i < PrimeBits.length; i++) {
                PrimeBits[i] = true;
            }
            for (int i = 1; i <= upperSqrt; i++) {
                if (PrimeBits[i]) {
                    for (int j = i * 2 * (i + 1); j <= sieveBound; j += 2 * i + 1) {
                        PrimeBits[j] = false;
                    }
                }
            }

            //List numbers = new ArrayList();
            int[] numbers = new int[sieveBound+1];
            //((int)(upperLimit / (Math.Log(upperLimit) - 1.08366)));
            numbers[0] = 2;
            for (int i = 1; i <= sieveBound; i++) {
                if (PrimeBits[i]) {
                    numbers[i] = (2 * i + 1);
                }
            }
            return numbers;
        }
 }

