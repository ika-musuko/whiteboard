import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;

public class DText extends DShape{	
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
	protected void draw(Graphics g) {
        TextInfo ti = (TextInfo)this.info;
        g.setColor(this.info.getColor());
        g.setFont(ti.getFont());
        int width = g.getFontMetrics().stringWidth(ti.getText());
        if(width != ti.getWidth())
        	ti.setWidth(g.getFontMetrics().stringWidth(ti.getText()));
        Rectangle bounds = ti.getBounds();
        g.drawString(ti.getText(), bounds.x, bounds.y+bounds.height);
	}
}
