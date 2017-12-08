import java.awt.*;
import java.awt.event.*;

public abstract class DShape implement InfoListener{
    protected Info info; 
    protected abstract void draw(Graphics g); // override this method to decide how to draw

    public DShape(){
    	this.setInfo(new Info());
    }
    
    public DShape(Info info) {
        this.setInfo(info);
    }

    public infoChanged(Info info){
        this.setInfo(info);
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

}
