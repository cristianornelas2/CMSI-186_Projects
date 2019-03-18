/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Provides a class defining and including methods from Timer.java and Ball.java
 *  @author       :  Cristian R. Ornelas
 *  Date written  :  2019-03-06
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 4
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class SoccerSim {
public double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
private final double FIELD_WIDTH = 1000;
private final double FIELD_HEIGHT = 1000;
private final double SECTION_1_WIDTH = 500;
private final double SECTION_2_HEIGHT = 500;
private final double SECTION_3_WIDTH = -500;
private final double SECTION_4_HEIGHT = -500;
public final static double X_POLE = 100;
public final static double Y_POLE = -25;


public int numberofballs;
private Ball[] ballsArr;
public double timeSlice;
public boolean collision = false;

public SoccerSim (String args[]) {
	numberofballs = (int)(args.length / 4);
if (args.length % 4 == 1) {
	timeSlice = Double.parseDouble(args[args.length-1]);
}
else if (args.length % 4 == 0) {
	timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
}
else {
	throw new IllegalArgumentException();
}

ballsArr = new Ball[numberofballs];
int h = 0;
 for (int i = 0; i < args.length-1 ; i+= 4) {
 	double xPosition = Double.parseDouble(args[i+0]);
 	double yPosition = Double.parseDouble(args[i+1]);
 	double xVelocity = Double.parseDouble(args[i+2]);
 	double yVelocity = Double.parseDouble(args[i+3]);
 	ballsArr[h] = new Ball(xPosition, yPosition, xVelocity, yVelocity, timeSlice);
 	h++;
 }
}

public void validateLocation() {
	for(Ball ball : ballsArr) {
		if (ball.getXLocation() > SECTION_1_WIDTH || ball.getYLocation() < SECTION_2_HEIGHT) {
			throw new IllegalArgumentException();
		}
		if (ball.getXVelocity() > SECTION_3_WIDTH || ball.getYVelocity() < SECTION_4_HEIGHT) {
			throw new IllegalArgumentException();
		}
	}
}

public void validateVelocity() {
	for(Ball ball : ballsArr) {
		if (ball.getXLocation() > SECTION_1_WIDTH || ball.getYLocation() < SECTION_2_HEIGHT) {
			throw new IllegalArgumentException();
		}
		if (ball.getXVelocity() > SECTION_3_WIDTH || ball.getYVelocity() < SECTION_4_HEIGHT) {
			throw new IllegalArgumentException();
		}
	}
}

public boolean Rest() {
for (Ball ball : ballsArr){
if (!ball.still()) {
return false;
      }
    }
return true;
  }


public boolean Collisions() {
int count = 1;
for(Ball ball : ballsArr) {
if(Math.sqrt(Math.pow(X_POLE - ball.getXLocation(),2) + (Math.pow(Y_POLE - ball.getYLocation(), 2))) < Ball.RADIUS_IN_INCHES) {
System.out.println("There has been a collision with - Ball " + count + "at [" + ball.getXLocation() + "," + ball.getYLocation() + "]");
       collision = true;
return true;
     }
for(Ball two_ball : ballsArr) {
if(Math.sqrt(Math.pow(two_ball.getXLocation() - ball.getXLocation(),2) + (Math.pow(two_ball.getYLocation() - ball.getYLocation(), 2)))
< Ball.RADIUS_IN_INCHES && ball != two_ball) {
System.out.println("There has been a collision at [" + ball.getXLocation() + "," + ball.getYLocation() + "]");
         collision = true;
return true;
       }
     }
     count++;
   }
return false;
 }


public String toString() {
int count = 1;
String result = "";
String resting = "Rest";
for(Ball ball : ballsArr) {
       result += "ball " + count + ": " + ball.toString() + "\n";
if(Rest()) {
         result += (resting + "\n");
       }
       count++;
     }
return result;
}

public static void main(String[] args) {
SoccerSim soccersim = new SoccerSim(args);
Timer timer = new Timer(soccersim.timeSlice);
System.out.println("REPORT \n ----------");
System.out.println(timer);
System.out.println(soccersim);
try {
      soccersim.validateLocation();
      soccersim.validateVelocity();
while(!soccersim.Rest() && !soccersim.Collisions()) {
for(Ball ball : soccersim.ballsArr) {
          ball.move();
        }
        timer.tick();
System.out.println(timer);
System.out.println(soccersim);
      }
if(!soccersim.Collisions) {
System.out.println("NO COLLISION POSSIBLE");
      }
    }
catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }

}
