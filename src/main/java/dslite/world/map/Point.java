package dslite.world.map;

public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int mDist(Point p1, Point p2) {
        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;
        return Math.abs(xDiff) + Math.abs(yDiff);
    }

    public static int euclideanDist(Point p1, Point p2) {
        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;
        return xDiff * xDiff + yDiff * yDiff;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
