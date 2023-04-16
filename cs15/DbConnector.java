import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:30306/pcmanager";

    // DB ID/PW
    private static final String dbUser = "root";
    private static final String dbPassword = "Skarnjsdn1!";
    private static Connection connection;

    public static Connection init() {
        try {
            //드라이버 로딩(mySQL)
            Class.forName(dbDriver);

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("DB Connection [성공]");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            if (connection != null & !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
