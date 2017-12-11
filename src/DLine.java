import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class DLine extends DShape {
    
	public DLine(){
		this.setInfo(new LineInfo());
	}
	
	public DLine(LineInfo info){
		super(info);
	}
    
    @Override
    public DShape copy() {
        return new DLine(new LineInfo((LineInfo)this.info));
    }
    
    @Override
    public void drawKnobs(Graphics g) {
        for (Point p : this.info.getKnobs()){
            g.setColor(Color.BLACK);
            g.fillRect(p.x-(KNOB_SIZE/2), p.y-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE);
        }
    }
	
    public void draw(Graphics g) {
        Rectangle bounds = this.info.getBounds();
        g.setColor(this.info.getColor());
        g.drawLine(this.info.getX(), this.info.getY(), ((LineInfo)this.info).getX2(), ((LineInfo)this.info).getY2());
    }
    
}
