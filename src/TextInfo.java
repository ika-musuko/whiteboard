import java.awt.Color;

class TextInfo extends Info {
    private String face;

    public TextInfo(String face, Color color, double x, double y, double w, double h) {
        super(color, x, y, w, h);
        this.face = face;
    }

    public TextInfo(double x, double y, double w, double h){
        this("Arial", Color.GRAY, x, y, w, h);
    }

    public TextInfo(String face, double x, double y, double w, double h) {
        this(face, Color.GRAY,  x, y, w, h);
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
