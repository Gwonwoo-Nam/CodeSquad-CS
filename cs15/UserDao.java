
import java.time.LocalDateTime;

public class UserDao {
    public User registerUser(int seatNumber) {
        String sql = String.format("INSERT INTO user_list(seat_number) VALUES(%d)", seatNumber);
        JdbcTemplate.query(sql);

        sql = String.format("SELECT * FROM user_list WHERE seat_number=%d AND finish_time IS NULL",seatNumber);
        int userId = JdbcTemplate.getIntQuery(sql);

        return new User(userId, LocalDateTime.now(),seatNumber);
    }

    public void recordFinishTime(int userId) {
        String sql = String.format("UPDATE user_list SET finish_time=CURRENT_TIMESTAMP WHERE user_id=%d", userId);
        JdbcTemplate.query(sql);
    }

    public int findUserSeatById (int userId) {
        String sql = String.format("SELECT seat_number FROM user_list WHERE user_id=%d",userId);
        int seatNumber = JdbcTemplate.getIntQuery(sql);

        return seatNumber;
    }

}
