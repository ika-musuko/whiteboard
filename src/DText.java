import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

public class DText extends DShape{	
	protected static final int KNOB_SIZE = 9;
    
    public DText(){
		this.setInfo(new TextInfo());
	}
	
	public DText(TextInfo info){
		super(info);
	}
    
    @Override
    public DShape copy() {
        return new DText(new TextInfo((TextInfo)this.info));
    }
    
    @Override
    public void drawKnobs(Graphics g) {
        for (Point p : this.info.getKnobs()){
            g.setColor(Color.BLACK);
            g.fillRect(p.x-(KNOB_SIZE/2), p.y-(KNOB_SIZE/2), KNOB_SIZE, KNOB_SIZE); // why
        }
    }
	
	@Override
	protected void draw(Graphics g) {
		TextInfo ti = (TextInfo)this.info;
		g.setColor(this.info.getColor());
        g.setFont(ti.getFont());
        ti.setWidth(g.getFontMetrics().stringWidth(ti.getText()));
        g.setClip(this.info.getBounds());
        g.drawString(ti.getText(), ti.getX(), ti.getY()+ti.getHeight()-(ti.getHeight()/5));
	}
}
