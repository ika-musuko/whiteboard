import java.awt.*;
import java.util.ArrayList;

class TextInfo extends Info {
    private String font;
    private String text;

    public TextInfo(){
        this("Default", "Arial", Color.GRAY, 10, 10, 200, 75);
    }

    public TextInfo(String text, String font, Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
        this.font = font;
        this.text = text;
    }

    public TextInfo(String text, int x, int y, int w, int h){
        this(text, "Arial", Color.GRAY, x, y, w, h);
    }

    public TextInfo(TextInfo i) {
        this.x         = i.x;
        this.y         = i.y;
        this.w         = i.w;
        this.h         = i.h;
        this.color     = new Color(i.color.getRGB());
        this.listeners = new ArrayList<>(i.listeners);  
        this.font      = new String(i.font);
        this.text      = new String(i.text);
    }    
 
    public String getText() {
        return this.text;
    }

    public Font getFont() {
        return new Font(this.font, Font.PLAIN, this.h);
    }  
    
    public String getFontName(){
    	return this.font;
    }

    public void setText(String text) {
        this.text = text;
        this.notifyListeners();
    }

    public void setFont(String font) {
        this.font = font;
        this.notifyListeners();
    }
    
}
