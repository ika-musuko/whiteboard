import java.awt.*;
import java.util.*;

public class RectangleUtils {
    public static ArrayList<Point> rectPoints(Rectangle r){
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(r.x, r.y));
        list.add(new Point((int) (r.x+r.getWidth()), r.y));
        list.add(new Point(r.x, (int) (r.y+r.getHeight())));
        list.add(new Point((int) (r.x+r.getWidth()),(int)( r.y+r.getHeight())));
        return list;
    }
}
