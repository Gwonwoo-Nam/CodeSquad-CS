import java.sql.Connection;

public class PcController {
    private PcService pcService = new PcService();
    private Connection connection;


    public void runCafe() {
        connection = DbConnector.init();
        JdbcTemplate.initConnection(connection);
        pcService.init();

        while (true) {
            String command = InputView.readCommand();
            if (command.isEmpty()) {
                System.out.println("시스템 종료.");
                break ;
            }
            pcService.run(command);
        }
        pcService.close();
    }

}
