package utils;

public class Direction {

    private int x;
    private int y;

    private int length = 0;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getLength() { return length; }
    public void setLength(int l) { this.length = l; }
}
