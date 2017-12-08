import java.awt.Color;

class TextInfo extends Info {
    private String face;

    public TextInfo(String face, Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
        this.face = face;
    }

    public TextInfo(int x, int y, int w, int h){
        this("Arial", Color.GRAY, x, y, w, h);
    }

    public TextInfo(String face, int x, int y, int w, int h) {
        this(face, Color.GRAY,  x, y, w, h);
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
