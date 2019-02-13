/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Implemented in this is a Textual User Interface (TUI) on the command line for the user to interact with.
 *  Author        :  Cristian R. Ornelas
 *  Date          :  2019-02-12
 *  Description   :  Implemented in this is a Textual User Interface (TUI) on the command line. This will display a 
 *                   list of options to the user, and will prompt for input. Based on that input this 
 *                   program will do what the user selected, then will display the results, a blank line 
 *                   or two, and then re-display the options.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class HighRoll{
    public static void main(String [] args){
        int numOfArg = args.length;
        if(numOfArg != 2) {
            System.out.println("ERROR : java HighRoll <Number of Dice> <Number of Sides>");
            throw new IllegalArgumentException();
        }
        
        char quitOption = 'a';
        int highScore = 0;
        int sum=0;
        int numOfDice = Integer.parseInt(args[0]); // changes the string arguments to integers
        int numOfSides = Integer.parseInt(args[1]); // changes the string arguments to integers
        
        DiceSet diceObj = new DiceSet(numOfDice, numOfSides); // assigning them to a new DiceSet
        
        Scanner myObj = new Scanner(System.in); // sets myObj to the input commanded by the user
        
        while(quitOption != 'Q'){
            System.out.println("Option 1: ROLL ALL THE DICE");
            System.out.println("Option 2: ROLL A SINGLE DIE");
            System.out.println("Option 3: CALCULATE THE SCORE FOR THIS SET");
            System.out.println("Option 4: SAVE THIS SCORE AS HIGH SCORE");
            System.out.println("Option 5: DISPLAY THE HIGH SCORE");
            System.out.println("Option 6: ENTER 'Q' TO QUIT THE PROGRAM");
            String opt = myObj.next();
            
            switch (opt.charAt(0)){
                case '1':
                    diceObj.roll();
                    break;
                case '2':
                    System.out.println("Which Die? ");
                    int index = myObj.nextInt();
                    int newdie = diceObj.rollIndividual(index-1);
                    System.out.println("[" + newdie + "]");
                    break;
                case '3':
                    sum = diceObj.sum(); 
                    System.out.println(sum);
                    break;
                case '4':
                    highScore = diceObj.sum();
                    break;
                case '5':
                    System.out.println(highScore);
                    break;
                case 'Q': 
                    quitOption = 'Q';
                    break;
                default:
                    break;
            }
        }
    }
}

// Number of dice = 3
// Number of sides = 10
// (1,2,3)
// [0,1,2]

// 3 times

// 1st time - score was 3
// 2nd       -  10  ----------> case 3 highscore = 10
// 3rd time - score is 20 -> case 3 wasn't executed then highScore will remain 10
