
public class Riemann {
	public static void main(String[] args) {
		if (args[0].equals("runtests")){
			runMyTests();
			return;
		}
		if (args.length < 3 && !args[0].equals("runtests")) {
			throw new IllegalArgumentException("Insufficient args");
		}
		double lowerBound;
		double upperBound;
		double percentage = 1;
		int LastCoeffIndex = 2;
		if (args[args.length-1].endsWith("%")){
			LastCoeffIndex = 3;
		}
		double[] coefficients = new double[args.length - LastCoeffIndex];
		try {
			for (int i = 1; i < args.length - LastCoeffIndex; i++) {
				coefficients[i-1] = Double.parseDouble(args[i]);
			}
			lowerBound = Double.parseDouble(args[args.length - LastCoeffIndex]);
			upperBound = Double.parseDouble(args[args.length - LastCoeffIndex + 1]);
		if (LastCoeffIndex == 3){
			String lastarg = args[args.length-1];
			String percent = lastarg.substring(0,lastarg.length() - 1);
			percentage = Double.parseDouble(percent);
			if (percentage <= 0.0) {
	          throw new IllegalArgumentException("Percent must be greater than 0.");
		}
	    }
		} catch (NumberFormatException e) {
			System.out.println(e);
			throw new IllegalArgumentException("All args must be nunmbers.");
		}
		if (lowerBound >= upperBound) {
			throw new IllegalArgumentException("Lower bound must be less than upper bound");
		}
		double width = upperBound - lowerBound;
		double area1 = 0;
		double area2 = 0;
		double x;
		double height;
		double rectArea;
		do {
			x = 
			lowerBound;
			area1 = area2;
			area2 = 0;
			while (x < upperBound) {
				if (args[0].equals("sin")){
					height = Math.sin(x + width/2.0);
				} else if (args[0].equals("cos")) {
					height = Math.cos(x + width/2.0);
				} else if (args[0].equals("log")) {
					height = Math.log(x + width/2.0);
				} else if (args[0].equals("sqrt")) {
					height = Math.sqrt(x + width/2.0);
				} else if (args[0].equals("exp")) {
					height = Math.exp(x + width/2.0);
				} else {
				height = evaluateFn(coefficients, x + width/2.0);
				}
				rectArea = height*width;
				area2 += rectArea;
				x += width;
			}
			width = width/2;
		} while (Math.min(Math.abs(area1), Math.abs(area2))/Math.max(Math.abs(area1), Math.abs(area2)) < (1.0 - percentage/100.0));
		System.out.println(area2);
	}
	public static double evaluateFn(double[] coefficients, double x) {
		double result = 0.0;
		for (int i = 0; i < coefficients.length; i++) {
			result = result + (coefficients[i] * Math.pow(x, i));
		}
		return result;
	}

private static void runMyTests(){
String[] arg1 = {
 	"poly" , "1.0" , "-2.1" , "3.2" , "-10.0" ,  "5.0"
};
main(arg1);
String[] arg2 = {
 	"poly" , "1.0" , "-2.1" , "3.2" , "-10.0" ,  "5.0" , "1.0%"
};
main(arg2);
String[] arg3 = {
 	"poly" , "0.0" , "8.0" , "-2.0" , "1.0" ,  "4.0" , "1e-6%"
};
main(arg3);
String[] arg4 = {
 	"sin" , "-0.27" , "+3.55"
};
main(arg4);
String[] arg5 = {
 	"log" , "1.0" , "2.3" 
};
main(arg5);
String[] arg6 = {
 	"exp" , "2.0" , "3.5" 
};
main(arg6);
String[] arg7 = {
 	"sqrt" , "1.0" , "2.0" 
};
main(arg7);
}
}
