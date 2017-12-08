import java.awt.*;
import java.awt.event.*;

public class DLine extends DShape {
	public DLine(){
		super();
	}
	
	public DLine(Info info){
		super(info);
	}
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        // start point is (x,y) and end point is (width, height)
        g.drawLine(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
