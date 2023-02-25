public class PcService {
    private InitDao initDao;
    private UserDao userDao;
    private ComputerDao computerDao = new ComputerDao();

    public void init() {

        initDao = new InitDao();
        //데이터 초기화
        initDao.createUserTable();
        initDao.createPcTable();
        initDao.initPcTable();

        //오픈 메시지 출력
        OutputView.printOpenMessage();
        computerDao.getEmptySeats();
    }

    public void run(String command) {
        userDao = new UserDao();

        if (Command.NEW.match(command)) {
            // 자리 할당
            Command.NEW.run(userDao, computerDao);
            return ;
        }
        if (Command.STOP.match(command)) {
            // 자리 정리
            Command.STOP.setValue(Integer.parseInt(command.split(" ")[1]));
            Command.STOP.run(userDao, computerDao);
        }
    }

    public void close() {
        initDao.clearTables();
        DbConnector.close();
    }


}
