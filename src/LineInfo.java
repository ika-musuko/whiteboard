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

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY; 
    }
}
