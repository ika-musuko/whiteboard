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
        g.setFont(ti.getFont());
        g.setClip(ti.getX(), ti.getY(), ti.getWidth(), ti.getHeight()*3/2);
        g.drawString(ti.getText(), ti.getX(), ti.getY()+ti.getHeight());
	}
}
