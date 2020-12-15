public class Index {
    int x;
    int y;
    String direction;
    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Index(int x, int y, String direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(String direction) {
        this.direction = direction ;
    }

    public String getDirection(){
        return this.direction;
    }
}
