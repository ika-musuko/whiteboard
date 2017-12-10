import java.awt.Graphics;

public class DLine extends DShape {
	public DLine(){
		this.setInfo(new LineInfo());
	}
	
	public DLine(LineInfo info){
		super(info);
	}
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.setClip(this.info.getRect());
        g.drawLine(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
    
}
