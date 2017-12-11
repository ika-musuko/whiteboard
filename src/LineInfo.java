import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Point;

public class LineInfo extends Info {
    private int x2;
    private int y2;
    
    public LineInfo() {
        this(Color.GRAY, 10, 10, 30, 30);
    }
    
    public LineInfo(Color color, int x1, int y1, int x2, int y2) {
        this.color = color;
        this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public LineInfo(LineInfo i) {
        this.x         = i.x;
        this.y         = i.y;
        this.x2        = i.x2;
        this.y2        = i.y2;
        this.color     = new Color(i.color.getRGB());
        this.listeners = new ArrayList<>(i.listeners);  
    }
    
    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }    

    public void setX2(int x) {
        this.x2 = x;
        this.notifyListeners();
    }

    public void setY2(int y) {
        this.y2 = y;
        this.notifyListeners();
    }
       
    @Override
    public void move(int x, int y){
        int x2dif = this.x2 - this.x;
        int y2dif = this.y2 - this.y;
        this.setX(x);
        this.setY(y);
        this.setX2(this.x+x2dif);
        this.setY2(this.y+y2dif);
    } 
        
    public void moveEndpoint(int x, int y) {
        this.setX2(x);
        this.setY2(y);        
    }
    
    @Override
    public void resize(Point anchor, int x, int y) {
        Point p1 = new Point(this.x, this.y);
        Point p2 = new Point(this.x2, this.y2);
        if (RectangleUtils.closerPoint(anchor, p1, p2) == p1) {
            this.moveEndpoint(x, y);
            return;
        }
        super.move(x, y);
    }
    
    @Override
    public List<Point> getKnobs() {
        List<Point> knobbies = new ArrayList<>();
        knobbies.add(new Point(this.x, this.y));
        knobbies.add(new Point(this.x2, this.y2));
        return knobbies;
    }
    
    @Override
    public Rectangle getBounds() {
        int knobOffset = DShape.KNOB_SIZE/2;
        Rectangle bounds = RectangleUtils.rectFromTwoPoints(new Point(this.x, this.y), new Point(this.x2, this.y2));
        bounds.grow(knobOffset, knobOffset);
        return bounds;
    }
    
    @Override
    public boolean contains(int x, int y) {
        return RectangleUtils.rectFromTwoPoints(new Point(this.x, this.y), new Point(this.x2, this.y2)).contains(x, y);
    }
    
}
