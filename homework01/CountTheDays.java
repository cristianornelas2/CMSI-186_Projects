public class CountTheDays {
  
  public static void main(String []args){
  
    CalendarStuff c = new CalendarStuff();
     System.out.println(c.daysBetween(Long.valueOf(args[0]),Long.valueOf(args[1]),Long.valueOf(args[2]),Long.valueOf(args[3]),Long.valueOf(args[4]),Long.valueOf(args[5])));
  }
}
