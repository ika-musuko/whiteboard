import java.awt.*;
import java.util.*;

public class RectangleUtils {
    public static List<Point> rectPoints(Rectangle r){
        return new ArrayList<Point>(
                                     new Point(r.getX()             , r.getY()              )
                                    ,new Point(r.getX()+r.getWidth(), r.getY()              )
                                    ,new Point(r.getX()             , r.getY()+r.getHeight())
                                    ,new Point(r.getX()+r.getWidth(), r.getY()+r.getHeight())
                                   );
    }
}
