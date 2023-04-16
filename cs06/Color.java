public enum Color {
    BLACK, WHITE;

    public Color getNextTurn() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

}
