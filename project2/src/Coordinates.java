// Written by Phanx289 & Benkh008
public class Coordinates {
    private int x;
    private int y;
    public static void main(String[] args) {

    }
    // a coordinate class that creates coordinate objects that are later stores in boat objects. this is to make keeping track of the ordered pairs of coordinate easier
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // a getter method that returns the x coordinate of a pair object
    public int getX() {
        return x;
    }
    // a getter method that returns the y coordinate of a pait of coordinate objects
    public int getY() {
        return y;
    }
}
