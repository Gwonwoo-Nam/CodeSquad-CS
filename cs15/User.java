import java.time.LocalDateTime;

public class User {
    private int userId;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private int seatNumber;

    public User(int userId, LocalDateTime startTime, int seatNumber) {
        this.userId = userId;
        this.startTime = startTime;
        this.seatNumber = seatNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
