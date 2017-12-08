import java.awt.Rectangle;
import java.awt.Color;

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
}
