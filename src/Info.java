import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Info {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    protected List<InfoListener> listeners;

    public Info() {
        this(10, 10, 100, 100);
    }

    public Info(int x, int y, int w, int h){
        this(Color.GRAY, x, y, w, h);
    }

    public Info(Color color, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.listeners = new ArrayList<>();
    }

    public String toString() {
        return "INFO (model) - x:"+this.x+" y:"+this.y+" w:"+this.w+" h:"+h;
    }

    protected Rectangle getBounds() {
        return (new Rectangle(this.x, this.y, this.w, this.h));
    }

    public boolean contains(int x, int y){
    	return this.getBounds().contains(x, y);
    }

    public Color getColor() {
        return this.color;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return this.h;
    }

    public List<Point> getKnobs() {
        return new ArrayList<Point>(Arrays.asList(
                                                  new Point(this.x       , this.y       )
                                                 ,new Point(this.x+this.w, this.y       )
                                                 ,new Point(this.x       , this.y+this.h)
                                                 ,new Point(this.x+this.w, this.y+this.h)
                                                 ));
    }

    protected void notifyListeners() {
        for (InfoListener il : this.listeners)
            il.infoChanged(this);
    }

    public void setColor(Color color) {
        this.color = color;
        this.notifyListeners();
    }

    public void setX(int x) {
        this.x = x;
        this.notifyListeners();
    }

    public void setY(int y) {
        this.y = y;
        this.notifyListeners();
    }

    public void setWidth(int w) {
        this.w = w;
        this.notifyListeners();
    }

    public void setHeight(int h) {
        this.h = h; 
        this.notifyListeners();
    } 

    public void addListener(InfoListener il){
        this.listeners.add(il);
    }
}
