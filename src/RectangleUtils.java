import java.awt.*;
import java.util.*;

public class RectangleUtils {
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
}
