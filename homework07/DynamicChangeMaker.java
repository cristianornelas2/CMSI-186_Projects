
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  ChangeMaker class
 * @author    :  Cristian R. Ornelas
 * Date       :  2019-05-08
 * Description:  This program takes as input arguments a sequence of coin denominations [distinct positive
 *               integers in no particular order], followed by an arbitrary amount of money [a non-negative
 *               integer], and which outputs the optimal way of making change for that amount using those 
 *               denominations [optimal meaning the minimum number of coins], or if change cannot be made, 
 *               outputs the message IMPOSSIBLE.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.util.Arrays;

public class DynamicChangeMaker {

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  makeChangeWithDynamicProgramming Method is used to validate all the denominations and targetCents. 
*  It also outputs the optimal way of making the targetCents for that amount using those denominations.
*  @throws  NumberFormatException if something is wrong
*  @return  Returns the optimal number of coins in which the targetCents has been acquired.
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    
public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int targetCents ) {
      try {
        for (int i = 0; i < denominations.length; i++) {
          String checkInt = Integer.toString(denominations[i]);
          if (denominations[i] < 1) {
            System.out.println("BAD DATA ----- MUST BE POSITIVE INTEGER GREATER THAN ZERO");
            System.exit(-1);
          }
        }
        if (targetCents < 0) {
          System.out.println("BAD DATA ----- MUST BE POSITIVE VAUE");
          System.exit(-1);
        }
        int[] testingDuplicates = denominations;
        for (int i = 0; i < denominations.length; i++) {
          for (int j = i+1; j < testingDuplicates.length-1; j++) {
            if (denominations[i] == testingDuplicates[j]) {
              System.out.println("BAD DATA ----- DENOMINATIONS SHOULD NOT BE REPEATED (AKA. NO DUPLICATES)");
              System.exit(-2);
            }
          }
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "BAD DATA" );
        System.exit(-1);
      }

      Tuple denomination = new Tuple(denominations);
      int rCount = denominations.length;
      int cCount = targetCents + 1;
      Tuple[][] table = new Tuple[rCount][cCount];
      for (int i = 0; i < rCount; i++) {
        for( int j = 0; j < cCount; j++ ) {
          if (j == 0) {
            table[i][j] = new Tuple(rCount);
          } else {
              if (denomination.getElement(i) > j) {
                table[i][j] = Tuple.IMPOSSIBLE;
              } else {
                Tuple goesIn = new Tuple(rCount);
                goesIn.setElement(i,1);
                table[i][j] = goesIn;
              }
              if ((j-denomination.getElement(i)) >= 0) {
                if ((table[i][j-denomination.getElement(i)].isImpossible()) || (table[i][j].isImpossible())) {
                  table[i][j] = Tuple.IMPOSSIBLE;
                } else {
                  table[i][j] = table[i][j].add(table[i][j-denomination.getElement(i)]);
                }
              }
              if (i != 0) {
                if (!(table[i-1][j].isImpossible())) {
                  if (table[i][j].isImpossible()) {

                    table[i][j] = table[i-1][j];
                  } else if (table[i-1][j].total() < table[i][j].total()) {
                    table[i][j] = table[i-1][j];
                  }
                }
              }
          }
        }
      }
      return table[rCount-1][cCount-1];
}
/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Main method is implemented so users could run this program with any number of denominations and targetCents
*  of their own and see the optimal outcome - if change cannot be made, outputs the message IMPOSSIBLE. 
*  @param  args  String array which contains the users command line arguments 
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


public static void main( String[] args ) {
      System.out.println( "\n  ----- Dynamic Change Maker ----- \n" );
      try {
        String inputArgDenoms = args[0];
        int inputTargetCents = Integer.parseInt(args[1]);

        String[] cents = inputArgDenoms.split(",");
        int[] inputDenoms = new int[cents.length];
        for (int i = 0; i < cents.length; i++) {
          inputDenoms[i] = Integer.parseInt(cents[i]);
        }
        Tuple result = makeChangeWithDynamicProgramming(inputDenoms,inputTargetCents);

        System.out.println("In order to make " + inputTargetCents + " cents - YOU NEED : \n");
        for (int i = 0; i < result.length(); i++) {
          System.out.println("NEED  " + "(" + result.getElement(i) + ")" + "  [" + inputDenoms[i] + " cent] coins");
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "----- BAD DATA! -----" );
        System.exit(-1);
      }
    }
}