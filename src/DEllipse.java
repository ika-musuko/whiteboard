import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class DEllipse extends DShape {
    public DEllipse(Color color, double x, double y, double w, double h) {
        super(color, x, y, w, h);
        this.shape = new Ellipse2D.Double();
        this.updateShape();
    }

    public DEllipse(double x, double y, double w, double h) {
       this(Color.GRAY, x, y, w, h);
    }

    protected void updateShape() {
        ((Ellipse2D.Double)this.shape).setFrame(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }

    public Shape getShape() {
        return this.shape;
    }

}
