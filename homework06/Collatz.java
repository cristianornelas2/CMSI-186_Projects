import java.io.*; 

public class Collatz {
     private BrobInt CollatzNumber = null; 
        
        private static String one = "1";
        private static String two = "2";
        private static String three = "3";

        public Collatz(String value){
            CollatzNumber = new BrobInt(value);
        }

        public BrobInt longDivision(BrobInt gint, int b)
        {
            int[] c = new int [10000];
            String dividend = gint.toString();
            
            int la;
            int i,k=0,flag=0;
            int temp=1;
            la=dividend.length();
            
            int [] a = new int[la];

            for(i=0;i<la;i++){
                a[i] = dividend.charAt(i) - 48;
            }

            temp = a[0];
    
           
            for(i=1;i<=la;i++){

             if(b<=temp){
                 c[k++] = temp/b;
                 temp = temp % b;

                 if(i<la)
                     temp =temp*10 + a[i];
                 else
                     temp =temp*10;
                 flag=1;

             }
             else{
                 if(i<la)
                     temp =temp*10 + a[i];
                 else
                     temp =temp*10;
                 
                 if(flag==1)
                     c[k++] = 0;
             }
        }

        String strArray = "";
        
        for(int j=0;j<k;j++){
            strArray += String.valueOf(c[j]);
        }

        String ans = strArray;

        BrobInt d = new BrobInt(ans);

        return d;

        }


        public void printCollatz() {
            
            BrobInt one_ = new BrobInt(one); 
            BrobInt two_ = new BrobInt(two); 
            BrobInt three_ = new BrobInt(three);

            BrobInt counter = new BrobInt(one);
            
            // We simply follow steps 
            // while we do not reach 1 

            while (CollatzNumber.compareTo(one_) > 0 ){ 

                System.out.print("\n step = ");
                System.out.println(counter.toString());
                System.out.println(CollatzNumber.toString()); 
                
                
                

                String r = CollatzNumber.toString();
                int lastIndx = r.length();
                
                if(lastIndx == 0) break;

                //System.out.println(lastIndx);
                char msb = r.charAt(lastIndx-1);

                // If n is odd 
                if (msb == '1' || msb == '3' || msb == '5' || msb == '7' || msb == '9') {
                    
                    CollatzNumber = CollatzNumber.multiply(three_);
                    
                    CollatzNumber = CollatzNumber.add(one_);
                    
                }
          
                // If even 
                else {
                    
                    CollatzNumber = longDivision(CollatzNumber, 2); 
                    
                }
                // Print 1 at the end
                
                counter = counter.add(one_);
                
            } 
        } 
}
