import java.awt.*;
import java.util.*;

public class RectangleUtils {
    // return the four corners of a rectangle
    public static ArrayList<Point> rectPoints(Rectangle r) {
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(r.x, r.y));
        list.add(new Point(r.x+r.width, r.y));
        list.add(new Point(r.x, r.y+r.height));
        list.add(new Point(r.x+r.width,r.y+r.height));
        return list;
    }
    
    // given x and y coords and a rectangle, return the opposite corner of the rect
    public static Point oppositeCorner(Rectangle r, int x, int y) {
        ArrayList<Point> corners = rectPoints(r);
        double farthestDistance = 0;
        Point farthestPoint = new Point();
        for (Point c : corners) {
            double cdist = Math.hypot(x-c.x, y-c.y);
            if (cdist > farthestDistance){
                farthestDistance = cdist;
                farthestPoint = c;
            }
        }
        return farthestPoint;
    }
    
    // check if two points are equal
    public static boolean pointEquals(Point a, Point b) {
        return (a.x == b.x) && (a.y == b.y);
    }
    
    // given p, return q1 if q1 is closer and q2 if q2 is closer
    public static Point closerPoint(Point p, Point q1, Point q2) {
        return Math.hypot(p.x-q1.x, p.y-q1.y) < Math.hypot(p.x-q2.x, p.y-q2.y) ? q1 : q2;
    }
}
