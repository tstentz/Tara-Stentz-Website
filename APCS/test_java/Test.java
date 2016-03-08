public class Test{


    public static void main(String[] args){
        int x = 6;
        int y = 19;
        int z = 2;
        if(x > y)
            if (z > x)
                z++;
        else
            z -= 5;
        y += x;
       System.out.println(y.equals(x)); 
    
    }


}
