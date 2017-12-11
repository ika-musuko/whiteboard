import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Point;

public class LineInfo extends Info {
    public LineInfo() {
        this(Color.GRAY, 10, 10, 30, 30);
    }
    
    public LineInfo(Color color, int x1, int y1, int w, int h) {
        this.color = color;
        this.x = x1;
        this.y = y1;
        this.w = w;
        this.h = h;
    }
    
    public LineInfo(LineInfo i) {
        this.x         = i.x;
        this.y         = i.y;
        this.w         = i.w;
        this.h         = i.h;
        this.color     = new Color(i.color.getRGB());
        this.listeners = new ArrayList<>(i.listeners);  
    }
       
    @Override
    public void move(int x, int y){
        int wdif = this.w - this.x;
        int hdif = this.h - this.y;
        this.setX(x);
        this.setY(y);
        this.setWidth(this.x+wdif);
        this.setHeight(this.y+hdif);
    } 
        
    public void moveEndpoint(int x, int y) {
        this.setWidth(x);
        this.setHeight(y);        
    }
    
    @Override
    public void resize(Point anchor, int x, int y) {
        Point p1 = new Point(this.x, this.y);
        Point p2 = new Point(this.w, this.h);
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
        knobbies.add(new Point(this.w, this.h));
        return knobbies;
    }
    
    @Override
    public Rectangle getBounds() {
        int knobOffset = DShape.KNOB_SIZE/2;
        Rectangle bounds = RectangleUtils.rectFromTwoPoints(new Point(this.x, this.y), new Point(this.w, this.h));
        bounds.grow(knobOffset, knobOffset);
        return bounds;
    }
    
    @Override
    public boolean contains(int x, int y) {
        return RectangleUtils.rectFromTwoPoints(new Point(this.x, this.y), new Point(this.w, this.h)).contains(x, y);
    }
    
    @Override
    public boolean containsWithKnobs(int x, int y){
        // shape bounds
    	if (this.contains(x, y))
            return true;
        
        // knob bounds
        for(Point p : this.getKnobs()){
    		Rectangle knobRegion = new Rectangle(p.x-DShape.KNOB_SIZE/2, p.y-DShape.KNOB_SIZE/2, DShape.KNOB_SIZE, DShape.KNOB_SIZE);
            if(knobRegion.contains(x, y))
                return true; 
        }
        return false;
    }
}
