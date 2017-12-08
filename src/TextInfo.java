import java.awt.*;

class TextInfo extends Info {
    private Font font;
    private String text;

    public TextInfo(){
        this("Default", new Font("Arial", Font.PLAIN, 10), Color.GRAY, 10, 10, 200, 75);
    }

    public TextInfo(String text, Font font, Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
        this.font = font;
        this.text = text;
    }

    public TextInfo(String text, int x, int y, int w, int h){
        this(text, new Font("Arial", Font.PLAIN, 10), Color.GRAY, x, y, w, h);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.notifyListeners();
    }

    public Font getFont() {
        return this.font;
    }  

    public void setFont(Font font){
        this.font = font;
        this.notifyListeners();
    }
}
