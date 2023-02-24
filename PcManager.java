import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PcManager {
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://127.0.0.1:30306/pcmanager";

    // DB ID/PW
    private final String dbUser = "root";
    private final String dbPassword = "Skarnjsdn1!";
    private Connection connection;

    public void init() {
        try {
            //드라이버 로딩(mySQL)
            Class.forName(dbDriver);

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("DB Connection [성공]");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserTable() {
        String sql = "CREATE TABLE user_list("
                + "user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "start_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "finish_time DATETIME,"
                + "seat_number INT NOT NULL);";
        query(sql);
    }

    public void createPcTable() {
        String sql = "CREATE TABLE pc_list("
                + "user_id INT,"
                + "seat_number INT NOT NULL PRIMARY KEY);";
        query(sql);
    }

    public void initPcTable() {
        String sql;
        for (int id = 1; id <= 16; id++) {
            sql = String.format("INSERT INTO pc_list(user_id, seat_number) VALUES(null, %d);", id);
            query(sql);
        }
    }

    public int registerUser(int seatNumber) {
        String sql = String.format("INSERT INTO user_list(seat_number) VALUES(%d)", seatNumber);
        query(sql);
        sql = String.format("SELECT * FROM user_list WHERE seat_number=%d AND finish_time IS NULL",seatNumber);
        return getUserId(sql);
    }

    public int leaveUser(int userId) {
        String sql = String.format("UPDATE user_list SET finish_time=CURRENT_TIMESTAMP WHERE user_id=%d", userId);
        query(sql);
        int seatNumber = 0;
        sql = String.format("SELECT seat_number FROM user_list WHERE user_id=%d",userId);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                seatNumber = rs.getInt(1);
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = String.format("UPDATE pc_list SET user_id=NULL WHERE seat_number=%d",seatNumber);
        query(sql);
        return seatNumber;
    }

    public void assignSeat(int seatNumber, int userId) {
        String sql = String.format("UPDATE pc_list SET user_id=%d WHERE seat_number=%d",userId,seatNumber);
        query(sql);
    }

    public void printEmptySeat() {
        String sql = "SELECT * FROM pc_list WHERE user_id IS NULL";
        List<Integer> emptyList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                emptyList.add(rs.getInt(2));
            }
            System.out.println(emptyList);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserIdFromPcList(int seatNumber) {
        String sql = String.format("SELECT * FROM pc_list WHERE seat_number=%d", seatNumber);
        return getUserId(sql);
    }

    private int getUserId(String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt(1);
                statement.close();
                return userId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void clearTables() {
        String sql = "DROP TABLE user_list;";
        query(sql);
        sql = "DROP TABLE pc_list;";
        query(sql);
    }

    private void query(String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null & !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
