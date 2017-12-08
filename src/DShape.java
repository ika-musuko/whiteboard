import java.awt.*;
import java.awt.event.*;

public abstract class DShape {
    protected Info info; 
    protected abstract void draw(Graphics g); // override this method to decide how to draw

    public DShape(Info info) {
        this.setInfo(info);
    }

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
    
    public boolean contains(int x, int y){
    	return info.contains(x, y);
    }

	public void revertListeners() {
		info.revertListeners();
	}

	public void addListeners() {
		info.addListeners();
	}
}
