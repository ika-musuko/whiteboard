import java.awt.Graphics;

public class DText extends DShape{	
	public DText(){
		this.setInfo(new TextInfo());
	}
	
	public DText(TextInfo info){
		super(info);
	}
	
	@Override
	protected void draw(Graphics g) {
		TextInfo ti = (TextInfo)this.info;
		g.setColor(this.info.getColor());
        g.setFont(ti.getFont());
        ti.setWidth(g.getFontMetrics().stringWidth(ti.getText()));
        g.setClip(ti.getX()-4, ti.getY()-4, ti.getWidth()+9, ti.getHeight()+9);
        g.drawString(ti.getText(), ti.getX(), ti.getY()+ti.getHeight()-(ti.getHeight()/5));
	}
}
