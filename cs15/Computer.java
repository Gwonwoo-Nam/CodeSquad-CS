public class Computer {
    private int userId;
    private int seatNumber;

    public Computer(int userId, int seatNumber) {
        this.userId = userId;
        this.seatNumber = seatNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
