public class PcController {
    private PcManager pcManager = new PcManager();

    public void runCafe() {
        initPcCafe();

        while (true) {
            String command = InputView.readCommand();
            if (command.isEmpty()) {
                System.out.println("시스템 종료.");
                break ;
            }
            if (Command.NEW.match(command)) {
                // 자리 할당
                Command.NEW.run(this.pcManager, this);
            }
            if (Command.STOP.match(command)) {
                // 자리 정리
                Command.STOP.setValue(Integer.parseInt(command.split(" ")[1]));
                Command.STOP.run(this.pcManager, this);
            }
        }
        closePcCafe();
    }

    private void initPcCafe() {
        //데이터 초기화
        pcManager.init();
        pcManager.createUserTable();
        pcManager.createPcTable();
        pcManager.initPcTable();

        //오픈 메시지 출력
        OutputView.printOpenMessage();
        pcManager.printEmptySeat();
    }

    private void closePcCafe() {
        //데이터 제거
        pcManager.clearTables();
        pcManager.close();
    }
}
