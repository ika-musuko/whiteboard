import java.awt.*;
import java.util.*;

public class RectangleUtils {
    public static ArrayList<Point> rectPoints(Rectangle r){
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(r.x, r.y));
        list.add(new Point(r.x+r.width, r.y));
        list.add(new Point(r.x, r.y+r.height));
        list.add(new Point(r.x+r.width,r.y+r.height));
        return list;
    }
}
