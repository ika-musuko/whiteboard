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
        // start point is (x,y) and end point is (width, height)
        g.drawLine(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
