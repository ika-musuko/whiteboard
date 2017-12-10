import java.awt.*;
import java.awt.event.*;

public class DEllipse extends DShape {
	public DEllipse(){
		super();
	}
	
	public DEllipse(Info info){
		super(info);
	}
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.setClip(this.info.getRect());
        g.fillOval(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
