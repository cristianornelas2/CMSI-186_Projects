/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Cristian Ornelas
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

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static final double EPSILON_VALUE       = 0.1; 
   private double targetAngle                      = 0;
   private double timeSlice                        = 0;

        // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      } 
     if(args.length == 1) {
      targetAngle = Double.parseDouble(args[0]);
      timeSlice = DEFAULT_TIME_SLICE_SECONDS;
     }
     if(args.length == 2) {
      targetAngle = Double.parseDouble(args[0]);
      timeSlice = Double.parseDouble(args[1]);
     } 
     if(targetAngle < 0){
      System.out.println( "   Sorry you must enter a positive target angle\n");
      System.exit(1);
     }
     if(timeSlice <= 0){
      System.out.println( "   Sorry you must enter a positive non-zero time slice\n");
      System.exit(1);
     }
   }

   public double getTimeSlice(){
     return this.timeSlice;
   }

   public double getTargetAngle() {
     return this.targetAngle;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments(args);
      Clock clk = new Clock(cse.getTimeSlice());
      
      //System.out.println(clk.getTotalSeconds());
     double targetAngle = cse.getTargetAngle();
      //System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
       //                   "--------------------------\n" );
      //System.out.println( "  Creating a new clock: " );
      //Clock clock = new Clock(1.0);
      while(clk.getTotalSeconds() <= 43200){
        double diff = Math.abs(clk.getHandAngle() - targetAngle);
        if(diff <= EPSILON_VALUE){
          System.out.println("Angle between hour and minute hands = " + clk.getHandAngle());
           System.out.println("hour Hand Angle = " + clk.getHourHandAngle());
           System.out.println("The time at the angle  " + targetAngle + " is " + clk.toString());
        }
        //System.out.println(diff + " : " + clock.getHandAngle() + " : " + clock.toString());
        clk.tick();
      }
      //while(clk.getTotalSeconds() <= 43200) {
      //double ta = 
      //double ha = clk.getHandAngle();

      //double diff = Math.abs(ta - ha);

        // if(EPSILON_VALUE >= diff && diff >= 0.0){

      // System.out.println("Epsilon: " + EPSILON_VALUE);
      // System.out.println("ta-ha: " + (ta-ha));
      // System.out.println("ha-ta: " + (ha-ta));

      //    if(EPSILON_VALUE >= ta - ha && ta - ha >= 0 ||
      //     ha - ta <= EPSILON_VALUE && ha - ta >= 0) {
         

         //}
      //clk.tick();
      //}
      System.exit( 0 );
   }
}

// calculate hand angle

           // based on calculation is the hand angle at the current time equal to the target angle we are looking for
              // if it is then call c.toStrin() to output the time 

           // given hand angle  - target angle and wiggle room




