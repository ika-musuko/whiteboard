import java.awt.Color;

class Info {
    private double x;
    private double y;
    private double w;
    private double h;
    private Color color;

    public Info(double x, double y, double w, double h){
        this(Color.GRAY, x, y, w, h);
    }

    public Info(Color color, double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.w;
    }

    public double getHeight() {
        return this.h;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double w) {
        this.w = w;
    }

    public void setHeight(double h) {
        this.h = h; 
    }
    
    public void revertListeners() {
		
		
	}

	public void addListeners() {
		
		
	}
    
}
