class LineInfo extends Info {
    private int endX;
    private int endY;

    public LineInfo(int startX, int startY, int endX, int endY) {
        super(startX, startY);
        this.endX = endX;
        this.endY = endY;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public int setEndX(int endX) {
        this.endX = endX;
    }

    public int setEndY(int endY) {
        this.endY = endY; 
    }
}
