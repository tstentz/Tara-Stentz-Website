public class Point{

    private int x;
    private int y;

    Point(int x1, int y1)
    {
        x = x1;
        y = y1;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean hasSameXandY(){
        if (getX() == getY())
                return true;
        else
                return false;
    }
    public static void main(String[] args){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 3);
        System.out.println(p3.hasSameXandY());
        if(p1.x == p2.x && p1.y == p2.y){
            System.out.println("One");
        }
        if(p1.equals(p2)){
            System.out.println("Two");
        }
        if(p1.getX() == p2.getX() && p1.getY() == p2.getY()){
            System.out.println("Three");
        }
    
    }



}
