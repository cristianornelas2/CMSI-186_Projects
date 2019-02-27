/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Cristian R. Ornelas
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {
  /**
   *  Class field definintions go here
   */ 
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static  double totalSeconds = 0;
   private static  double timeSlice = 0;
   private static  double targetAngle = 0;
   private static  double hourHand = 0;



  /**
   *  Constructor goes here
   */
   public Clock(double timeSlice) {
    this.timeSlice = timeSlice;
   }
  
  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
    totalSeconds += timeSlice;
      return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double validateAngle = Double.parseDouble(argValue);
      if(validateAngle < 360 && validateAngle > 0) {
        return validateAngle;
      }
      throw new NumberFormatException();
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      double validateTimeSlice = Double.parseDouble(argValue);
      if (validateTimeSlice < 1800 && validateTimeSlice > 0){
        return validateTimeSlice;
      }
       return -1;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return HOUR_HAND_DEGREES_PER_SECOND * totalSeconds;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return MINUTE_HAND_DEGREES_PER_SECOND * totalSeconds;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double angle = Math.abs(getHourHandAngle() - getMinuteHandAngle());
      return angle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      // DecimalFormat df = new DecimalFormat("#0.###");
      // DecimalFormat df1 = new DecimalFormat("#0");
  
      // int hours = (int)(totalSeconds / 3600);
      // int mins = (int)(totalSeconds - (hours * 3600)/ 60);
      // double secs = (totalSeconds - (hours * 3600) - (mins * 60));
    

        int hours = (int)(totalSeconds / 3600);
        int remainder = (int) (totalSeconds % 3600);
        int mins = (int) (remainder / 60);
        double secs = (double) (remainder % 60.0);

      // String hString = df1.format(hours);
      // String mString = df1.format(mins);
      // String sString = df.format(secs);
        String hh = String.format("%02d", hours);
        String mm = String.format("%02d", mins);
        String zz = String.format("%.03f", secs);
      return (hh + ":" + mm + ":" + zz);
      // return (hString + ":" + mString + ":" + sString);
   }

   // DecimalFormat df = newDecimalformal("o#");
   // hString = df.format(hrs);

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {
      double targetAngle = 90.0;
      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock(1800.0);
      /*while(clock.getTotalSeconds() <= 43200){
        double diff = Math.abs(clock.getHandAngle() - targetAngle);
        if(diff <= 0.1000)
        System.out.println(diff + " : " + clock.getHandAngle() + " : " + clock.toString());
        clock.tick();
      }
      */

      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println(clock.getTotalSeconds());
      try { System.out.println( (80 == clock.validateAngleArg("80") ) ? " 80 is valid angle arg" : "80 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (25 == clock.validateAngleArg( "25" )) ? " 25 is valid angle arg" : " 25 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (359 == clock.validateAngleArg( "359" )) ? " 359 is valid angle arg" : " 359 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (360 == clock.validateAngleArg( "360" )) ? " 360 is valid angle arg" : " 360 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (3025 == clock.validateAngleArg( "2005" )) ? " 3025 is valid angle arg" : " 3025 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-2 == clock.validateAngleArg( "-2" )) ? " -2 is valid angle arg" : " -2 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-20000 == clock.validateAngleArg( "-20000" )) ? " -20000 is valid angle arg" : " -20000 is invalid angle arg" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-9 == clock.validateTimeSliceArg("-9") ) ? " -9 is valid time slice" : " -9 is invalid time slice" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (1799 == clock.validateTimeSliceArg("1799") ) ? " 1799 is valid time slice" : " 1799 is invalid time slice" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (1801 == clock.validateTimeSliceArg("1801") ) ? " 1801 is valid time slice" : " 1801 is invalid time slice" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (-1 == clock.validateTimeSliceArg("abc") ) ? " abc is valid time slice" : " abc is invalid time slice" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("Clock Tick = " + clock.tick());
      System.out.println("Clock Hour Hand Angle = " + clock.getHourHandAngle());
      System.out.println("Clock Minute Hand Angle = " + clock.getMinuteHandAngle());
      System.out.println("Clock Hand Angle = " + clock.getHandAngle());
      System.out.println("Clock Seconds = " + clock.getTotalSeconds());
      System.out.println("Analog Clock Time = " + clock.toString());
   
   }
}
