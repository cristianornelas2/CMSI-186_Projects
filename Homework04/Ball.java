/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Cristian R. Ornelas
 *  Date written  :  2019-03-06
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 4  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.text.DecimalFormat;

public class Ball { 
   public static final double RADIUS_IN_INCHES = 4.45;
   private static final double WEIGHT_IN_POUNDS = 1;
   private static final double FRICTION_PERCENT = 0.01;
   public static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
   public static final double FINISHED_X_VELO = 1/12;
   public static final double FINISHED_Y_VELO = 1/12;
   public double xball = 0;
   public double yball = 0;
   public double xballVelo = 0;
   public double yballVelo = 0;
   private double timeSlice = 0;

  /**
   *  Constructor goes here
   */
   public Ball (double xLocation, double yLocation, double xVelo, double yVelo, double timeSlice){
      xball = xLocation;
      yball = yLocation;
      xballVelo = xVelo;
      yballVelo = yVelo;
      this.timeSlice = timeSlice;

      if(timeSlice <= 0 || timeSlice > 1799){
      System.out.println( "   Sorry you must enter a positive non-zero time slice less then 1800\n");
      System.exit(1);
     }
   }

   public double getXLocation () {
   	return xball;
   }	

   public double getYLocation() {
   	return yball;
   }	

   public double getXVelocity (){
   	return xballVelo;
   }

   public double getYVelocity() {
   	return yballVelo;
   }

   public boolean still() {
    return Math.abs(xballVelo) <= .083 && Math.abs(yballVelo) <= .083;
  }

   public Ball (String args[]) {
      if (args.length % 4 == 1) {
        timeSlice = Double.parseDouble(args[args.length-1]);
      }
      else if (args.length % 4 == 0) {
        timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      }
     else {
        throw new IllegalArgumentException();
      }
   }

public void move() {
   System.out.println("timeSlice =" + timeSlice);
   xball += (xballVelo);
   yball += (yballVelo);
   xballVelo = xballVelo - ((xballVelo * FRICTION_PERCENT) * timeSlice);
   yballVelo = yballVelo - ((yballVelo * FRICTION_PERCENT) * timeSlice);
   if ((Math.abs(xballVelo) * 10) <= 1){
      xballVelo = 0;
   }
   if ((Math.abs(yballVelo) * 10) <= 1) {
      yballVelo = 0;
   }
}

public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    return "Position: " + "[" + df.format(xball) + ", " + df.format(yball) + "]" + " Velocity: " + "[" + df.format(xballVelo) + ", " + df.format(yballVelo) +"]";
  }

public static void main(String args[]) {
    System.out.println( "\nBALL CLASS TESTER PROGRAM\n");
    System.out.println( "  Creating a new ball... " );
    Ball ball = new Ball(10.0,50.0,2.0,6.0,1.0);
    System.out.println( "  New ball created: " + ball.toString());
    ball.move();
    System.out.println("Current: " + ball.toString());
    try { System.out.println( (12.0 == ball.xball) ? " move() for X-val working as intended" : " move() not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (56.0 == ball.yball) ? " move() for Y-val working as intended" : " move() not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (1.98 == ball.xballVelo) ? " move() for X-Velocity working as intended" : " move() not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (5.94 == ball.yballVelo) ? " move() for Y-Velocity working as intended" : " move() not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (1.0 == ball.timeSlice) ? " move() for timeSlice working as intended" : " move() not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }
}


