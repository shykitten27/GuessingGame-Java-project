/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package guessinggame;
import java.util.*;

/**
 *
 * @author Donna
 */
public class GuessingGame {
        static Scanner sc=new Scanner(System.in);
        static int lastCat, lastDiff, gameType;
    public static void main(String[] args) {
        String choice="";
        int n;
        System.out.println("Welcome to the guessing game!");
        System.out.println("Would you like to play? (Y/N): ");
        choice = sc.next();
        while (choice.equalsIgnoreCase("Y") ){
            try{
                System.out.print("Game type? 1=Hot/Cold, 2=High/Low: ");
                    gameType = sc.nextInt();
                if (gameType != 1 && gameType != 2) {
                System.out.println("Illegal type - default playing Hot/Cold...");
                }

            } catch (Exception e) {
                System.out.println("Illegal type - default playing Hot/Cold...");
                gameType=1; //force into Hot/Cold
                sc.next();
                        
                }
            n = getRandom();
            System.out.println("I am thinking of a number from 1 to 100...");
            playGame(n);
            System.out.print("Would you like to play again? (Y/N) ");
            choice = sc.next();
        }//end of while
        System.out.println("\nThanks for playing - see you next time! ");
    }//end of main
    public static int getRandom() {
        int rnum;
        Random r = new Random();
        rnum = r.nextInt(100) +1;
        return rnum;
    }//end of getRandom

    public static void playGame(int n) {
        int g, gCount=0;
        lastCat = 0;
        lastDiff = 0;
        do {
            System.out.print(" Your guess? (0=quit): ");
            try {
                g = sc.nextInt();
            } catch (Exception e){
                g = -1;
                sc.next();
            }
            if (g < 0 | g > 100) {
                System.out.println("Illegal guess; please enter a number from 1 to 100");
            } else if (g == 0) {
                System.out.println("Sorry you did not guess my number " + n + " in " + gCount + " guesses.");
            } else if (g == n) {
                //gCount++; //increment counter here or as incremented variable below
                System.out.println("You guessed in in " + (++gCount) + " tries!");
                g = 0;
            } else {
                gCount++;
                if (gameType == 1) {
					showHotCold(n,g);
				} else {
					showHighLow(n,g);
				}
            }
        } while (g != 0);
        return;

    }//end of playGame

    public static void showHighLow(int n, int g) {
		if (g > n) {
			System.out.println("Too high");
		} else
			System.out.println("Too low");
		}
	
    public static void showHotCold(int n, int g) {
        int d = Math.abs(n-g);
        String message = "";
        int category = 0;

        if (d >= 75) {
            message = "very cold";
            category = 1;

        } else if (d >= 50) {
            message = "cold";
            category = 2;

        } else if (d >= 25) {
            message = "warm";
            category = 3;

        } else if (d >= 11) {
            message = "very warm";
            category = 4;

        } else {
            message = "hot";
            category = 5;

        }
        if (category == lastCat) {
            if (d == lastDiff) {
                message = message + " same degree.";
            }else {
                switch (category) {
                    case 1:   //very cold
                    case 2:   //cold
                        if (d > lastDiff) {
                            message = message + " and getting colder";
                        } else {
                            message = message + " but getting warmer";
                        }
                        break;
                    case 3:  //warm
                    case 4:  //very warm
                        if (d > lastDiff) {
                            message = message + " but getting colder";
                        } else {
                            message = message + " and getting warmer";
                        }
                        break;
                    case 5:   //hot
                        if (d > lastDiff) {
                            message = message + " but getting colder";
                        } else {
                            message = message + " and getting hotter";
                        }
                        break;
                } // end of switch
            } //end of else for diff
        } //end of if category


        System.out.println(message);
        lastCat = category;
        lastDiff = d;
        return;
    }
}//end of class

