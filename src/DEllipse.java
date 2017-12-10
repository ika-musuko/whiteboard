import java.awt.*;
import java.awt.event.*;

public class DEllipse extends DShape {
	public DEllipse(){
		super();
	}
	
	public DEllipse(Info info){
		super(info);
	}
    
    @Override
    public DShape copy() {
        return new DEllipse(new Info(this.info));
    }
   
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.setClip(this.info.getBounds());
        g.fillOval(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
