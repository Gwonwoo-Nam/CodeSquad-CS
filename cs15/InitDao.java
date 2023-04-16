
public class InitDao {

    public void createUserTable() {
        String sql = "CREATE TABLE user_list("
                + "user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "start_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "finish_time DATETIME,"
                + "seat_number INT NOT NULL);";
        JdbcTemplate.query(sql);
    }

    public void createPcTable() {
        String sql = "CREATE TABLE pc_list("
                + "user_id INT,"
                + "seat_number INT NOT NULL PRIMARY KEY);";
        JdbcTemplate.query(sql);
    }

    public void initPcTable() {
        String sql;
        for (int id = 1; id <= 16; id++) {
            sql = String.format("INSERT INTO pc_list(user_id, seat_number) VALUES(null, %d);", id);
            JdbcTemplate.query(sql);
        }
    }
    public void clearTables() {
        String sql = "DROP TABLE user_list;";
        JdbcTemplate.query(sql);
        sql = "DROP TABLE pc_list;";
        JdbcTemplate.query(sql);
    }

}
