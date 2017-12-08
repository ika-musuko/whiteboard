import java.awt.*;

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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return this.font;
    }  

    public void setFont(Font font){
        this.font = font;
        this.notifyListeners();
    }

    public Font getFont() {
        return new Font(this.font, Font.PLAIN, this.h);
    }  

    public void setFont(String font){
        this.font = font;
        this.notifyListeners();
    }
}
