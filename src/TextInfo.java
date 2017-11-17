class TextInfo {
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

    public int setSize(int size) {
        this.size = size;
    }

    public String setFace(String face) {
        this.face = face;
    }
}
