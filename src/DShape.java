import java.awt.*;
import java.awt.Point;

public abstract class DShape implements InfoListener{
    protected Info info; 
    protected abstract void draw(Graphics g); // override this method to decide how to draw
    protected static final int KNOB_SIZE = 9;

    public void drawKnobs(Graphics g) {
        for (Point p : this.info.getKnobs()){
            g.setColor(Color.BLACK);
            g.fillRect((int)p.getX()-(KNOB_SIZE/2), (int)p.getY()-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE); // why
        }
    }

    // InfoListener
    @Override
    public void infoChanged(Info info){
        this.setInfo(info);
    }

    public DShape(){
    	this.setInfo(new Info());
    }
    
    public DShape(Info info) {
        this.setInfo(info);
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
}
