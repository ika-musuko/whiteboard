import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

public abstract class DShape implements InfoListener{
    protected Info info; 
    protected static final int KNOB_SIZE = 18;
    
    // InfoListener implement
    @Override
    public void infoChanged(Info info) {
        this.setInfo(info);
    }
    
    protected DShape() {
    	this.setInfo(new Info());
    }
    
    protected DShape(Info info) {
        this.setInfo(info);
    }
    
    protected DShape(DShape ds) {
        this.info = new Info(ds.info);
    }
    
    public Info getInfo() {
        return this.info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
    
    public boolean contains(int x, int y){
    	return info.contains(x, y);
    }
    
    // get the opposite end of the bounds rectangle
    public Point getAnchor(int x, int y){
    	for(Point p : this.info.getKnobs()){
    		Rectangle knobRegion= new Rectangle(p.x-KNOB_SIZE/2, p.y-KNOB_SIZE/2, KNOB_SIZE, KNOB_SIZE);
    		if(knobRegion.contains(x, y))
    			return RectangleUtils.oppositeCorner(this.info.getBounds(), x, y);
    	}
    	return Canvas.NOANCHOR;
    }
    
    public void drawKnobs(Graphics g) {
        for (Point p : this.info.getKnobs()){
            g.setColor(Color.BLACK);
            g.fillRect(p.x-(KNOB_SIZE/2), p.y-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE); // why
        }
    }
    
    protected abstract void draw(Graphics g); // override this method to decide how to draw
    public abstract DShape copy();         // copy objects
}
