public enum File {
    A(0, "A"),
    B(1, "B"),
    C(2, "C"),
    D(3, "D"),
    E(4, "E"),
    F(5, "F"),
    G(6, "G"),
    H(7, "H");
    private String file;
    private int loc;

    File(int loc, String file) {
        this.file = file;
        this.loc = loc;
    }

    public int getLoc() {
        return loc;
    }

    public static File getFileByIndex(int index) {
        return switch (index) {
            case 0 -> A;
            case 1 -> B;
            case 2 -> C;
            case 3 -> D;
            case 4 -> E;
            case 5 -> F;
            case 6 -> G;
            case 7 -> H;
            default -> throw new IllegalArgumentException("[ERROR] index Out of Bounds");
        };
    }

    @Override
    public String toString() {
        return file;
    }
}