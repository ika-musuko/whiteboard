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
		g.setColor(ti.getColor());
		g.drawString(ti.getText(), ti.getX(), ti.getY());
		
	}

}
