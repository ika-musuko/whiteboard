import java.awt.*;
import java.awt.event.*;

public class DRectangle extends DShape {
    public void draw(Graphics g) {
        g.setColor(this.info.getColor());
        g.fillRect(this.info.getX(), this.info.getY(), this.info.getWidth(), this.info.getHeight());
    }
}
