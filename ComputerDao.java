import java.util.List;

public class ComputerDao {
    public void assignSeat(int seatNumber, int userId) {
        String sql = String.format("UPDATE pc_list SET user_id=%d WHERE seat_number=%d",userId,seatNumber);
        JdbcTemplate.query(sql);
    }
    public void getEmptySeats() {
        String sql = "SELECT * FROM pc_list WHERE user_id IS NULL";
        List<Integer> emptySeats = JdbcTemplate.getIntsQuery(sql);
        OutputView.printEmptySeats(emptySeats);
    }

    public int getUserIdFromPcList(int seatNumber) {
        String sql = String.format("SELECT * FROM pc_list WHERE seat_number=%d", seatNumber);
        int userId = JdbcTemplate.getIntQuery(sql);

        return userId;
    }
    public void clearSeat(int seatNumber) {
        String sql = String.format("UPDATE pc_list SET user_id=NULL WHERE seat_number=%d",seatNumber);
        JdbcTemplate.query(sql);
    }
}
