import java.awt.Graphics;

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
        ti.setWidth(g.getFontMetrics().stringWidth(ti.getText()));
        g.setClip(this.info.getBounds());
        g.drawString(ti.getText(), ti.getX(), ti.getY()+ti.getHeight()-(ti.getHeight()/5));
	}
}
