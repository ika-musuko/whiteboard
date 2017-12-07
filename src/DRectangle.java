import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class DRectangle extends DShape {
	
	public DRectangle(){
		super();
	}

	public DRectangle(Color color, double x, double y, double w, double h) {
        super(color, x, y, w, h);
        this.shape = new Rectangle2D.Double();
        this.updateShape();
	}
	
	public DRectangle(double x, double y, double w, double h) {
	       this(Color.GRAY, x, y, w, h);
	}
	
	@Override
	public Shape getShape() {
		return this.shape;
	}

	@Override
	protected void updateShape() {
		((Rectangle2D.Double)this.shape).setFrame(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
	}

}
