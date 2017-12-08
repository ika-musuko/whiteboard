import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class DEllipse extends DShape {
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.fillOval(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
