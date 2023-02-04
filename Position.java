public class Position {

    private File file;

    enum File {
        A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H");
        private String file;

        File(String file) {
            this.file = file;
        }
    }

    private Rank rank;

    enum Rank {
        ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8");
        private String rank;

        Rank(String rank) {
            this.rank = rank;
        }
    }

    Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }


}
