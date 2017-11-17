class TextInfo extends Info {
    private int size;
    private String face;

    public TextInfo(int x, int y, int size, String face) {
        super(x, y);
        this.size = size;
        this.face = face;
    }

    public int getSize() {
        return this.size;
    }

    public String getFace() {
        return this.face;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
