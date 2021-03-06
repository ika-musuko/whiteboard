import java.awt.*;

public class DRectangle extends DShape {
	public DRectangle(){
		super();
	}
	
	public DRectangle(Info info){
		super(info);
	}
    
    @Override
    public DShape copy() {
        return new DRectangle(new Info(this.info));
    }
	
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        Rectangle bounds = this.info.getBounds();
        g.fillRect(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
