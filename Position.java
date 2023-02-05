public class Position {

    private File file;

    private Rank rank;



    Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    Position(int rankIndex, int fileIndex) {
        this.file = File.getFileByIndex(fileIndex);
        this.rank = Rank.getRankByIndex(rankIndex);
    }

    public int getFileLocation() {
        return file.getLoc();
    }

    public int getRankLocation() {
        return rank.getLoc();
    }

    public boolean equals(Position position) {
        if (this.getFileLocation() == position.getFileLocation() &&
            this.getRankLocation() == position.getRankLocation()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
