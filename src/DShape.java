import java.awt.*;
import java.awt.event.*;

public abstract class DShape {
    protected Info info;

    protected DShape() {
    	this(Color.GRAY, 10.0, 10.0, 20.0, 20.0);
    }

	protected DShape(Color color, double x, double y, double w, double h){
        this.info = new Info(color, x, y, w, h);
    }

    public DShape(double x, double y, double w, double h) {
        this.info = new Info(x, y, w, h);
    }
 
    protected abstract void draw(Graphics g); // override this method to decide how to draw

    public void select() {
        this.info.select();
    }

    public void deselect() {
        this.info.deselect();
    }


    public Info getInfo() {
        return this.info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
    
    public boolean contains(double x, double y){
    	return shape.contains(x, y);
    }

	public void revertListeners() {
		info.revertListeners();
	}

	public void addListeners() {
		info.addListeners();
	}
}
