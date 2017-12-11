import java.awt.Graphics;
import java.awt.Color;

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
    
    // only draw the line endpoint knobs
    @Override
    public void drawKnobs(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.info.getX()-(KNOB_SIZE/2), this.info.getY()-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE);
        g.fillRect(this.info.getWidth()-(KNOB_SIZE/2), this.info.getHeight()-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE);
    }
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.setClip(this.info.getBounds());
        g.drawLine(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
    
}
