/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <cristian ornelas>
 *  Date          :  <January 28, 2019>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 */
import java.time.YearMonth;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.text.DateFormatSymbols;
import java.time.Month;


public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    days  = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   // private static int[]    months = {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER};

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      
      if ((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)) {
          return true;
      }
          return false;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */

   // Had help form a tutor on this method - I also had another method/idea to test 
   // IF a year was a leap year AND also if the month was February then by looking at the 
   // private static int[] days  = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; 
   // February would be int[2] days which = 28, and becasue its also leap year + 1 ((int[]days) + 1).
   // ELSE Every month besides February has the same number of days in their month whether 
   // its a leap year or not (int[]days).  
      
      public static long daysInMonth( long month, long year ) {
      YearMonth yearMonthObject = YearMonth.of((int)year, (int)month);
      long daysInMonth = yearMonthObject.lengthOfMonth();
      return daysInMonth;
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if((month1 == month2) && (day1 == day2) && (year1 == year2)) { //if one of them fails the condition then the dates do not equal.
        return true; 
      }
        return false;
    }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      
      int result = (int)(year1 - year2); // if the years are the same then result = 0. If they are different the result would be negative - If the result is negative then firstDate is less than secondDate.
      
         if(result == 0) {
            result = (int)(month1 - month2); // Same as the condition regarding years applies here.
               if(result == 0) {
                   result = (int)(day1 - day2); // same as months and years condition applies here.
        }
      }
   
         if(result == 0) { // 0 = no difference between the dates so they are equal
         return 0;
         } else if(result > 0) { // if the first date, year1, month1, or day1, which ever is greater than second, then that leaves a positive result. Therefore firstDate is greater then secondDate.
         return 1;
         } else {  // If the first date is less than second, that leaves a negitive result
         return -1;
      }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   
   // Had help from a tutor on this method - by re-formating the month, day, and year through SimpleDateFormat. If setLenient is true, rather than false
   // it would allow for flexibility and allow for dates to be misinterpretted...false is strict and throws an exception of dated no entered correctly.

   public static boolean isValidDate( long month, long day, long year ) {
     
    String formatDate = year+"/"+month+"/"+day;
       
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    sdf.setLenient(false);
   
    return sdf.parse(formatDate, new ParsePosition(0)) != null;
    
   }





  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
    
    // switch statement that allows for the case # to correlate to the output of that specific month in order.

    String output = "";
    switch(month) {
        
      case 1: output = "January"; 
        break;
      case 2: output = "February";
        break;
      case 3: output = "March";
        break;
      case 4: output = "April";
        break;
      case 5: output = "May";
        break;
      case 6: output = "June";
        break;
      case 7: output = "July";
        break;
      case 8: output = "August";
        break;
      case 9: output = "September";
        break;
      case 10: output = "October";
        break;
      case 11: output = "November";
        break;
      case 12: output = "December";
        break;   
    
      default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
           
    }

    return output;
    
  }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
    
    // switch statement that allows for the case # to correlate to the output of that specific week-day in order.

    String output = "";
    switch(day) {
        
      case 1: output = "Sunday";
        break;
      case 2: output = "Monday";
        break;
      case 3: output = "Tuesday";
        break;
      case 4: output = "Wednesday";
        break;
      case 5: output = "Thursday";
        break;
      case 6: output = "Friday";
        break;
      case 7: output = "Saturday";
        break;
    
      default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
           
    }

    return output;
    
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
    public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      
      if(dateEquals(month1,day1,year1,month2,day2,year2)){
         return 0;
      } // if they equal there are 0 dated in between
    
      if(!isValidDate(month1,day1,year1) || !isValidDate(month2,day2,year2)){
         return -1;
      } by using the past method isValidDate; if date1 or date2 is invalid then returns -1

        Calendar cal1 = new GregorianCalendar(); // taught to me by my tutor - cal1 and cal2 are dates set/formatted to the function of GregorianCalendar
        Calendar cal2 = new GregorianCalendar();

        cal1.set((int)year1,(int) month1,(int) day1); // date1 and date2 are implementted/re-formatted to the Calendar
        cal2.set((int)year2,(int) month2,(int) day2);
    
        return (Math.abs(daysBtw(cal1.getTime(),cal2.getTime()))); // returns the time
    }

    public static long daysBtw(Date d1, Date d2) {
        return (long)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)); // by subtracting the time between d2 and d1; we then multiply that amount by milliseconds * seconds * hours * and day to get the final result.
    }
 }

