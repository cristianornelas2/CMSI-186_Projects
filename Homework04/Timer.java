/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Cristian R. Ornelas
 *  Date written  :  2019-03-06
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 4 
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 public class Timer { 
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS= 1.0;
   private static final double inchesInFeet = 12.0;
   private static double totalSeconds = 0;
   private static double timeSlice = 0;



  /**
   *  Constructor goes here
   */
   public Timer () {
   	timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   }  

   public Timer (double timeSlice) {
    this.timeSlice = timeSlice;
   }

   public double tick() {
    totalSeconds += timeSlice;
      return totalSeconds;
   }

   public static double getTotalSeconds () {
    return totalSeconds;
   }

   public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
      double validateTimeSlice = Double.parseDouble(argValue);
      if (validateTimeSlice < 1800 && validateTimeSlice > 0){
        return validateTimeSlice;
      }
       throw new NumberFormatException();
   }

  public static int getHours() {
  	int hours = (int)(totalSeconds / 3600);
  	return hours;
  }

  public static int getMins() {
  	int remainder = (int) (totalSeconds % 3600);
    int mins = (int) (remainder / 60);
  	return mins;
  }

  public static double getSecs() {
    int remainder = (int) (totalSeconds % 3600);
  	double secs = (double) (remainder % 60.0);
  	return secs;
  }

  public String toString() {
        int hours = (int)(totalSeconds / 3600);
        int remainder = (int) (totalSeconds % 3600);
        int mins = (int) (remainder / 60);
        double secs = (double) (remainder % 60.0);


        String hh = String.format("%02d", hours);
        String mm = String.format("%02d", mins);
        String zz = String.format("%.03f", secs);
      
      	return (hh + ":" + mm + ":" + zz);
  }
}

  public static void main(String[] args) {

      Timer timer = new Timer();

      System.out.println("Testing with a timeSlice of 15 with 15 ticks.");
      timeSlice = 15;
      double numSecs = 0;
      for(int i = 0; i < 15; i++) {
        System.out.println(timer.toString());
        timer.tick();
      }
      System.out.println(timer.getTotalSeconds());
  }
