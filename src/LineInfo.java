import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Point;

public class LineInfo extends Info {
    
    public LineInfo(){
        this(30, 30, 15, 15);
    }

    public LineInfo(int x1, int y1, int x2, int y2){
        this(Color.GRAY, x1, y1, x2, y2);
    }

    public LineInfo(Color color, int x1, int y1, int x2, int y2){
        super(color, x1, y1, x2, y2);
    }
    
    public LineInfo(LineInfo i) {
        this.x         = i.x;
        this.y         = i.y;
        this.w         = i.w;
        this.h         = i.h;
        this.color     = new Color(i.color.getRGB());
        this.listeners = new ArrayList<>(i.listeners);   
    }

    // for cases 1 and 3
    private Rectangle downwardSlope(int x1, int y1, int x2, int y2){
        return new Rectangle(x1, y1, x2-x1, y2-y1);
    }
    
    // for cases 2 and 4
    private Rectangle upwardSlope(int x1, int y1, int x2, int y2){
        return new Rectangle(x1, y2, x2-x1, y1-y2);
    }
    
    @Override
    protected Rectangle getBounds(){
        
        // case 1-2
        if(this.x < this.w){
            return this.y < this.h ? this.downwardSlope(this.x, this.y, this.w, this.h)    // case 1
                                   : this.upwardSlope(this.x, this.y, this.w, this.h);// case 2
        }
        // case 3-4
        return this.y > this.h ? this.downwardSlope(this.w, this.h, this.x, this.y) // case 3
                               : this.upwardSlope(this.w, this.h, this.x, this.y); // case 4

    }

    @Override
    public boolean contains(int x, int y){
        //System.out.println("LINETHIS: "+this);
    	Rectangle b = this.getBounds();
        //System.out.println(b);
        return b.contains(x, y);
    }
    
    @Override
    public List<Point> getKnobs() {
        List<Point> knobbies = new ArrayList<>();
        knobbies.add(new Point(this.x, this.y));
        knobbies.add(new Point(this.w, this.h));
        return knobbies;
    }
    
    

    @Override
    public void move(int x, int y) {
        Rectangle newBounds = this.getBounds();
        newBounds.setLocation(x, y);
        List<Point> points = RectangleUtils.rectPoints(newBounds);
        int p1 = 0, p2 = 3;
        if (this.x < this.w){
            if(this.y < this.h){
                p1 = 0;
                p2 = 3;
            }
            else {
                p1 = 2;
                p2 = 1;
            }
        }
        else{
            if(this.y > this.h){
                p1 = 3;
                p2 = 0;
            }
            else {
                p1 = 1;
                p2 = 2;
            }
        }
        this.setX(points.get(p1).x);
        this.setY(points.get(p1).y);
        this.setWidth(points.get(p2).x);
        this.setHeight(points.get(p2).y);
    }
    
    @Override
    public void resize(Point anchor, int x, int y) {
        if (anchor.x == this.x && anchor.y == this.y){
            this.setWidth(x);
            this.setHeight(y);
            return;
        }
        super.move(x, y);
    }
}
