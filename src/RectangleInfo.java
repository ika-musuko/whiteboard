
public class RectangleInfo extends ShapeInfo {

	private int width;
	private int height;
	
	public RectangleInfo(int x, int y, int width, int height){
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setWidth(int w){
		this.width = w;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
}
