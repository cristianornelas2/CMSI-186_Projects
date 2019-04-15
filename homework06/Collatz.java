import java.io.*; 
  
class Collatz  { 
 
 BrobInt CollatzNumber; 
 public Collatz(BrobInt n){
    CollatzNumber = n;
 }
    public void printCollatz() {
        BrobInt one = new BrobInt("1"); 
        BrobInt two = new BrobInt("2"); 
        BrobInt three = new BrobInt("3");  
        BrobInt counter = new BrobInt("1");
        System.out.println("Before While Loop ");
        while (CollatzNumber.compareTo(one) == 1) { 
            System.out.print("\n step = ");
            System.out.println(counter.toString());
            System.out.println(CollatzNumber.toString()); 
            System.out.println("1");
            if ((CollatzNumber.remainder(two)).equals(one)) {
                System.out.println("2");
                CollatzNumber = CollatzNumber.multiply(three);
                System.out.println("3"); 
                CollatzNumber = CollatzNumber.addInt(one);
                System.out.println("4");
            }
            else {
                System.out.println("5");
                CollatzNumber = CollatzNumber.divide(two); 
                System.out.println("6");
            }
            System.out.println("7");
            counter = counter.addInt(one);
            System.out.println("8");
            break;
        } 
    } 
} 
