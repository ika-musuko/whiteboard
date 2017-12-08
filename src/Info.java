import java.awt.Color;
import java.awt.Rectangle;

class Info {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    protected boolean selected;
   
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
        this.selected = false;
    }

    public Color getColor() {
        return this.color;
    }
    
    public boolean contains(int x, int y){
    	return (new Rectangle(x,y,w,h).contains(x, y));
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public void setHeight(int h) {
        this.h = h; 
    }
    
   
    public boolean isSelected(){
        return this.selected;
    }

    public void select() {
        this.selected = true;
    }

    public void deselect() {
        this.selected = false;
    }

    public void revertListeners() {
		
		
	}

	public void addListeners() {
		
		
	}
    
}
