import java.awt.*;
import java.awt.event.*;

public abstract class DShape {
    protected Shape shape;
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

    public abstract Shape getShape(); // get the actual shape object for drawing on the canvas
    protected abstract void updateShape(); // internal method for updating the Swing Shape from the Info class
    public Info getInfo() {
        return this.info;
    }
    
    public boolean contains(double x, double y){
    	if(shape.contains(x, y)){
    		return true;
    	}
    	else
    		return false;
    }

	public void revertListeners() {
		info.revertListeners();
	}

	public void addListeners() {
		info.addListeners();
	}
    
    
}
