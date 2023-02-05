public enum Rank {
    ONE(0,"1"),
    TWO(1,"2"),
    THREE(2,"3"),
    FOUR(3,"4"),
    FIVE(4,"5"),
    SIX(5,"6"),
    SEVEN(6,"7"),
    EIGHT(7,"8");
    private String rank;
    private int loc;

    Rank(int loc, String rank) {
        this.loc = loc;
        this.rank = rank;
    }

    public static Rank getRankByIndex(int index) {
        return switch (index) {
            case 0 -> ONE;
            case 1 -> TWO;
            case 2 -> THREE;
            case 3 -> FOUR;
            case 4 -> FIVE;
            case 5 -> SIX;
            case 6 -> SEVEN;
            case 7 -> EIGHT;
            default -> throw new IllegalArgumentException("[ERROR] index Out of Bounds");
        };
    }

    public int getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return rank;
    }

}