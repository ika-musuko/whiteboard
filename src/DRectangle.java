import java.awt.*;

public class DRectangle extends DShape {
	public DRectangle(){
		super();
	}
	
	public DRectangle(Info info){
		super(info);
	}
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.fillRect(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
