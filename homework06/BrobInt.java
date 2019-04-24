/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobIntTester.java
 * Purpose    :  Test Harness for the BrobInt java class
 * @author    :  Cristian R. Ornelas
 * Date       :  2019-04-15
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  Had a lot of help and hours with my online tutor for this one - most difficult assignment yet but gladly got through it.
 * Warnings   :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
 *                                     sequence.  Added some tests into the main() method at the bottom
 *                                     of the file to test construction.  Also added two tests there to
 *                                     test multiplication by three and times-3-plus-1 operations
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

  public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
  public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
  public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
  public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
  public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
  public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
  public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
  public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
  public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
  public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
  public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
  public static final BrobInt MAX_INT  = new BrobInt(Integer.valueOf( Integer.MAX_VALUE ).toString() );
  public static final BrobInt MIN_INT  = new BrobInt(Integer.valueOf( Integer.MIN_VALUE ).toString() );
  public static final BrobInt MAX_LONG = new BrobInt(Long.valueOf( Long.MAX_VALUE ).toString() );
  public static final BrobInt MIN_LONG = new BrobInt(Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
  String internalValue = "";        // internal String representation of this BrobInt
  byte   sign          = 0;         // "0" is positive, "1" is negative
  /// You can use this or not, as you see fit.  The explanation was provided in class
  private String reversed      = "";        // the backwards version of the internal String representation
  byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;

  /**
  *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
  *   and handles that accordingly;  it then checks to see if it's all valid digits.
  *  @param  value  String value to make into a BrobInt
  */
  public BrobInt( String value ) {
    this.internalValue = value;
    this.byteVersion = this.validateDigits(this.internalValue);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  bint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to validate all digits, construct the byte array, and set the sign of the BrobInt
  *  @return  byte array with padding added in front for potential carry
  *  @throws  IllegalArgumentException if something is hinky
  *  note that there is no return false, because of throwing the exception
  *  note also that this must check for the '+' and '-' sign digits
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public byte[] validateDigits( String internalString) {
    int beginningNumber = 0;
    byte[] byteArray = new byte[internalValue.length() + 1];

    while (((int) internalString.charAt(beginningNumber) < (int) '0' || (int) internalString.charAt(beginningNumber) > (int) '9')) {
      beginningNumber += 1;
    }
    for (int i = 0; i < internalString.length(); i++) {
      if ((int) internalString.charAt(i) < (int) '0' || (int) internalString.charAt(i) > (int) '9') {
        if (((int) internalString.charAt(i) != (int) '-' && (int) internalString.charAt(i) != (int) '+') || i != beginningNumber - 1) {
          throw new IllegalArgumentException("Input contains at least one non-numeric input OR a misplaced sign.");
        }
      }
      if (beginningNumber != 0) {
        if (internalString.charAt(beginningNumber - 1) == '-') {
          this.sign = 1;
        } else {
          this.sign = 0;
        }
      }

      if (this.sign == 1 && i == beginningNumber - 1) {
        byteArray[i + 1] = (byte) 0;
      } else {
        byteArray[i + 1] = (byte) ((int) internalString.charAt(i) - 48);
      }

    }
    if (this.sign == 1) {
      byte[] tempArray = new byte[byteArray.length - 1];
      for (int i = 0; i < byteArray.length - 1; i++) {
        tempArray[i] = byteArray[i + 1];
      }
      return tempArray;
    }
    return byteArray;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
  *  @param  gint         BrobInt to add to this
  *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt add( BrobInt gint ) {
    byte localSign = 0;
    byte[] carryArray;
    byte[] sumArrayOne;
    byte[] sumArrayTwo;
    byte thisSign = this.sign;
    byte gintSign = gint.sign;
    BrobInt output;

    if (gint.sign == 1 && this.sign == 1) {
      localSign = 1;
    }

    if ((gint.sign == 1 && this.sign == 0) || (gint.sign == 0 && this.sign == 1)) {

      if (gint.sign == 1 && this.compareTo(gint.multiply(new BrobInt("-1"))) < 1) {

        gint.sign = 0;
        output = gint.subtract(this);
        gint.sign = 1;

        output.sign = 1;
      } else if (this.sign == 1 && gint.compareTo(this.multiply(new BrobInt("-1"))) > -1) {

        this.sign = 0;
        output = gint.subtract(this);
        this.sign = 1;
        output.sign = 0;
      } else if (gint.sign == 1 && this.compareTo(gint.multiply(new BrobInt("-1"))) > -1) {

        gint.sign = 0;
        output = gint.subtract(this);
        gint.sign = 1;
        output.sign = 1;
      } else {

        this.sign = 0;
        output = this.subtract(gint);
        this.sign = 1;
        output.sign = 1;
      }
    } else {

      if (gint.byteVersion.length >= this.byteVersion.length) {
        carryArray = new byte[gint.byteVersion.length];
        sumArrayOne = new byte[gint.byteVersion.length];
        sumArrayTwo = new byte[gint.byteVersion.length];
      } else {
        carryArray = new byte[this.byteVersion.length];
        sumArrayOne = new byte[this.byteVersion.length];
        sumArrayTwo = new byte[this.byteVersion.length];
      }

      for (int i = 0; i < carryArray.length; i++) {
        carryArray[i] = 0;
      }

      for (int i = gint.byteVersion.length - 1; i >= 0; i--) {
        sumArrayOne[sumArrayOne.length - 1 - i] = gint.byteVersion[gint.byteVersion.length - 1 - i];
      }

      for (int i = this.byteVersion.length - 1; i >= 0; i--) {
        sumArrayTwo[sumArrayTwo.length - 1 - i] = this.byteVersion[this.byteVersion.length - 1 - i];
      }

      for (int i = carryArray.length - 1; i >= 0; i--) {
        if (sumArrayOne[i] + sumArrayTwo[i] + carryArray[i] > 9) {
          if (i != 0) {
            carryArray[i - 1] = 1;
          }
          sumArrayOne[i] = (byte) (sumArrayOne[i] + sumArrayTwo[i] - 10);
        } else if (sumArrayOne[i] + sumArrayTwo[i] + carryArray[i] <= 9) {
          sumArrayOne[i] = (byte) (sumArrayOne[i] + sumArrayTwo[i]);
        }
      }

      for (int i = 0; i < carryArray.length; i++) {
      }
  
      for (int i = 0; i < sumArrayOne.length; i++) {
        sumArrayOne[i] = (byte) (sumArrayOne[i] + carryArray[i]);
      }

      String byteString = "";
      int beenHit = 0;

      if (localSign == 1) {
        byteString = byteString.concat( "-" );
      }

      for( int i = 0; i < sumArrayOne.length; i++ ) {
        if(sumArrayOne[i] != 0) {
          beenHit = 1;
        }
        if (beenHit == 0 && sumArrayOne[i] == 0) {

        } else {
          byteString = byteString.concat( Byte.toString( sumArrayOne[i] ) );
        }
      }

      if (byteString.length() == 0 || byteString.equals("-")) {
        byteString = "0";
      }

      output = new BrobInt(byteString);

    }
    this.sign = thisSign;
    gint.sign = gintSign;
    return output;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using bytes
  *  @param  gint         BrobInt to subtract from this
  *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt subtract( BrobInt gint ) {
    byte thisSign = this.sign;
    byte gintSign = gint.sign;
    BrobInt output;
    byte subtractionResult[];
    byte subtractant[];

    if (gint.byteVersion.length > this.byteVersion.length) {
      subtractionResult = new byte[gint.byteVersion.length];
      subtractant = new byte[gint.byteVersion.length];
      for (int i = this.byteVersion.length - 1; i >= 0; i--) {
        subtractionResult[(gint.byteVersion.length - this.byteVersion.length) + i] = this.byteVersion[i];
      }

      for (int i = gint.byteVersion.length - 1; i >= 0; i--) {
        subtractant[i] = gint.byteVersion[i];
      }
    } else {
      subtractionResult = new byte[this.byteVersion.length];
      subtractant = new byte[this.byteVersion.length];

      for (int i = this.byteVersion.length - 1; i >= 0; i--) {
        subtractionResult[i] = this.byteVersion[i];
      }
      
      for (int i = gint.byteVersion.length - 1; i >= 0; i--) {
        subtractant[(this.byteVersion.length - gint.byteVersion.length) + i] = gint.byteVersion[i];
      }
    }

    if (this.sign == 0 && gint.sign == 0) {
  
      if (this.compareTo(gint) == -1) {

        output = new BrobInt(((gint.subtract(this)).multiply(new BrobInt("-1"))).toString());
        this.sign = thisSign;
        gint.sign = gintSign;
        return output;
      } else {

        for (int i = subtractionResult.length - 1; i >= 0; i--) {
          if (subtractionResult[i] - subtractant[i] < 0) {
            subtractionResult[i - 1] = (byte) (subtractionResult[i - 1] - 1);
            subtractionResult[i] = (byte) (subtractionResult[i] - subtractant[i] + 10);
          } else if (subtractionResult[i] - subtractant[i] >= 0) {
            subtractionResult[i] = (byte) (subtractionResult[i] - subtractant[i]);
          }
        }
        String byteString = "";
        int beenHit = 0;

        for( int i = 0; i < subtractionResult.length; i++ ) {
          if(subtractionResult[i] != 0) {
            beenHit = 1;
          }
          if (beenHit == 0 && subtractionResult[i] == 0) {

          } else {
            byteString = byteString.concat( Byte.toString( subtractionResult[i] ) );
          }
        }

        if (byteString.length() == 0) {
          byteString = "0";
        }
        this.sign = thisSign;
        gint.sign = gintSign;
        return new BrobInt(byteString);
      }
    } else if ((this.sign == 0 && gint.sign == 1)) {

      output = new BrobInt((this.add(gint.multiply(new BrobInt("-1")))).toString());
      this.sign = thisSign;
      gint.sign = gintSign;
      return output;
    } else if (this.sign == 1 && gint.sign == 0) {

      output = new BrobInt((((this.multiply(new BrobInt("-1")).add(gint))).multiply(new BrobInt("-1"))).toString());
      this.sign = thisSign;
      gint.sign = gintSign;
      return output;
    } else {

      if (this.compareTo(gint) == -1) {

        output = new BrobInt((((this.multiply(new BrobInt("-1"))).subtract(gint.multiply(new BrobInt("-1")))).multiply(new BrobInt("-1"))).toString());
        this.sign = thisSign;
        gint.sign = gintSign;
        return output;
      } else {

        output = new BrobInt((((gint.multiply(new BrobInt("-1"))).subtract(this.multiply(new BrobInt("-1")))).toString()));
        this.sign = thisSign;
        gint.sign = gintSign;
        return output;
      }
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to multiply the value of a BrobInt passed as argument to this BrobInt
  *  @param  gint         BrobInt to multiply by this
  *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt multiply( BrobInt gint ) {
    if (gint.compareTo(new BrobInt("-1")) == 0) {
      if (this.sign == 1) {
        this.sign = 0;
      } else {
        this.sign = 1;
      }
      return this;
    }

    byte localSign = 0;
    if ((this.sign == 1 && gint.sign == 0) || (this.sign == 0 && gint.sign == 1)) {
      localSign = 1;
    }
    BrobInt index = new BrobInt("0");
    BrobInt smallMultiplicand;
    BrobInt revolvingSum = new BrobInt("0");
    BrobInt largeMultiplicand;

    byte tempThisSign = this.sign;
    byte tempGintSign = gint.sign;

    this.sign = 0;
    gint.sign = 0;

    if (this.compareTo(gint) == -1) {
   
      smallMultiplicand = new BrobInt(this.toString());
      largeMultiplicand = new BrobInt(gint.toString());
    } else {

      smallMultiplicand = new BrobInt(gint.toString());
      largeMultiplicand = new BrobInt(this.toString());
    }
    smallMultiplicand.sign = 0;
    largeMultiplicand.sign = 0;
    this.sign = tempThisSign;
    gint.sign = tempGintSign;

    while (index.compareTo(smallMultiplicand) < 0) {
      revolvingSum = revolvingSum.add(largeMultiplicand);
      index = index.add(new BrobInt("1"));
    }
    revolvingSum.sign = localSign;
    return revolvingSum;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
  *  @param  gint         BrobInt to divide this by
  *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt divide( BrobInt gint ) {
    byte localSign = 0;
    BrobInt index = new BrobInt("0");
    BrobInt numerator;
    BrobInt denominator;
    BrobInt revolvingSum = new BrobInt("0");

    if (gint.compareTo(new BrobInt("0")) == 0) {
      return new BrobInt("0");
    } else if (this.sign == 1 && gint.sign == 0) {
      localSign = 1;
      numerator = new BrobInt((this.multiply(new BrobInt("-1"))).toString());
      denominator = new BrobInt(gint.toString());
    } else if (this.sign == 0 && gint.sign == 1) {
      localSign = 1;
      numerator = new BrobInt(this.toString());
      denominator = new BrobInt((gint.multiply(new BrobInt("-1"))).toString());
    } else {
      numerator = new BrobInt(this.toString());
      denominator = new BrobInt(gint.toString());
    }
    if (gint.compareTo(this) == 0) {
      if (localSign == 0) {
        return new BrobInt("1");
      } else {
        return new BrobInt("-1");
      }
    } else if (gint.compareTo(this) == 1) {
      return new BrobInt("0");
    } else {
      while (numerator.compareTo(revolvingSum.add(denominator)) > -1) {
        revolvingSum = revolvingSum.add(denominator);
        index = index.add(new BrobInt("1"));
      }

      index.sign = localSign;
      return index;
    }
  }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to get the remainder of division of this BrobInt by the one passed as argument
  *  @param  gint         BrobInt to divide this one by
  *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt remainder( BrobInt gint ) {
    BrobInt divisionResult = new BrobInt((this.divide(gint)).toString());
    return this.subtract(divisionResult.multiply(gint));
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to compare a BrobInt passed as argument to this BrobInt
  *  @param  gint  BrobInt to add to this
  *  @return int   that is one of neg/0/pos if this BrobInt lessThan/equals/greaterThan the argument
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt gint ) {
    byte byteVersionLengthThis = 0;
    byte byteVersionLengthGint = 0;
    int beenHit = 0;
    
    for (int i = 0; i < this.byteVersion.length; i++) {
      if (this.byteVersion[i] > 0 || beenHit == 1) {
        beenHit = 1;
        byteVersionLengthThis += 1;
      }
    }
    
    beenHit = 0;
    for (int i = 0; i < gint.byteVersion.length; i++) {
      if (gint.byteVersion[i] > 0 || beenHit == 1) {
        beenHit = 1;
        byteVersionLengthGint += 1;
      }
    }

    if (this.sign == 0 && gint.sign == 1) {
      return 1;
    } else if (this.sign == 1 && gint.sign == 0) {
      return -1;
    } else if (this.sign == 0 && gint.sign == 0) {
      if ( byteVersionLengthThis > byteVersionLengthGint ) {
        return 1;
      } else if( byteVersionLengthThis < byteVersionLengthGint ) {
        return (-1);
      } else {
        for( int i = 0; i < this.byteVersion.length; i++ ) {
          if(byteVersion[i] > gint.byteVersion[i]) {
            return 1;
          } else if(byteVersion[i] < gint.byteVersion[i]) {
            return (-1);
          }
        }
      }
    } else if (this.sign == 1 && gint.sign == 1) {
      if ( byteVersionLengthThis > byteVersionLengthGint ) {
        return -1;
      } else if ( byteVersionLengthThis < byteVersionLengthGint ) {
        return 1;
      } else {
        for( int i = 0; i < this.byteVersion.length; i++ ) {
          if (byteVersion[i] > gint.byteVersion[i]) {
            return -1;
          } else if (byteVersion[i] < gint.byteVersion[i]) {
            return 1;
          }
        }
      }
    }
    return 0;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to check if a BrobInt passed as argument is equal to this BrobInt
  *  @param  gint     BrobInt to compare to this
  *  @return boolean  that is true if they are equal and false otherwise
  *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
  *        also using the java String "equals()" method -- THAT was easy, too..........
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean equals( BrobInt gint ) {
    return (internalValue.equals(gint.toString() ));
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to return a BrobInt given a long value passed as argument
  *  @param  value         long type number to make into a BrobInt
  *  @return BrobInt  which is the BrobInt representation of the long
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static BrobInt valueOf( long value ) throws NumberFormatException {
    BrobInt gi = null;
    try {
      gi = new BrobInt(Long.valueOf( value ).toString() );
    }
    catch( NumberFormatException nfe ) {
      System.out.println( "\n  Sorry, the value must be numeric of type long." );
    }
    return gi;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to return a String representation of this BrobInt
  *  @return String  which is the String representation of this BrobInt
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public String toString() {
    String byteString = "";
    int beenHit = 0;

    if (this.sign == 1) {
      byteString = byteString.concat( "-" );
    }

    for( int i = 0; i < this.byteVersion.length; i++ ) {
      if(this.byteVersion[i] != 0) {
        beenHit = 1;
      }
      if (beenHit == 0 && this.byteVersion[i] == 0) {

      } else {
        byteString = byteString.concat( Byte.toString( this.byteVersion[i] ) );
      }
    }
    if (byteString.length() == 0 || byteString.equals("-")) {
      byteString = "0";
    }
    return byteString;
  }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove leading zeros from a BrobInt passed as argument
   *  @param  bint     BrobInt to remove zeros from
   *  @return BrobInt that is the argument BrobInt with leading zeros removed
   *  Note that the sign is preserved if it exists
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt removeLeadingZeros( BrobInt bint ) {
      Character sign = null;
      String returnString = bint.toString();
      int index = 0;

      if( allZeroDetect( bint ) ) {
         return bint;
      }
      if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
         sign = returnString.charAt( index );
         index++;
      }
      if( returnString.charAt( index ) != '0' ) {
         return bint;
      }

      while( returnString.charAt( index ) == '0' ) {
         index++;
      }
      returnString = bint.toString().substring( index, bint.toString().length() );
      if( sign != null ) {
         returnString = sign.toString() + returnString;
      }
      return new BrobInt( returnString );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a boolean if a BrobInt is all zeros
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean allZeroDetect( BrobInt bint ) {
      for( int i = 0; i < bint.toString().length(); i++ ) {
         if( bint.toString().charAt(i) != '0' ) {
            return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  *  Method to display an Array representation of this BrobInt as its bytes
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void toArray( byte[] d ) {
    System.out.println( Arrays.toString( d ) );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display a prompt for the user to press 'ENTER' and wait for her to do so
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void pressEnter() {
    throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      BrobInt b1 = null;;
      try { System.out.println( "   Making a new BrobInt: " ); b1 = new BrobInt( "147258369789456123" ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      try { System.out.println( "   expecting: 147258369789456123\n     and got: " + b1.toString() ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      System.out.println( "\n    Multiplying 82832833 by 3: " );
      try { System.out.println( "      expecting: 248498499\n        and got: " + new BrobInt("82832833").multiply( BrobInt.THREE ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Multiplying 3 by 82832833 and adding 1: " );
      try { System.out.println( "      expecting: 248498500\n        and got: " + BrobInt.THREE.multiply( new BrobInt( "82832833" ) ).add( BrobInt.ONE ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
      System.exit( 0 );

   }
}
