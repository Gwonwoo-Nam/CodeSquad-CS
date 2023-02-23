import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        PcManager pcManager = new PcManager();
        //데이터 초기화
        pcManager.init();
        pcManager.createUserTable();
        pcManager.createPcTable();
        System.out.println("> 빈 자리는 다음과 같습니다.");

        pcManager.initPcTable();
        pcManager.printEmptySeat();

        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.isEmpty()) {
                System.out.println("시스템 종료.");
                break ;
            }
            if (command.equals("new")) {
                // 랜덤 자리 하나 뽑기
                int randomSeat = chooseRandomSeat(pcManager);
                int userId = pcManager.registerUser(randomSeat);
                pcManager.assignSeat(randomSeat,userId);
                System.out.println(randomSeat+"번 자리에 앉으세요 : #"+userId);
                pcManager.printEmptySeat();
            }
            if (command.matches("^stop [0-9]*$")) {

                int userId = Integer.parseInt(command.split(" ")[1]);
                int seatNumber = pcManager.leaveUser(userId);
                System.out.println("이제 "+seatNumber+"번 자리가 비었습니다.");
                pcManager.printEmptySeat();
            }
        }



        //데이터 제거
        pcManager.clearTables();
        pcManager.close();
    }

    private static int chooseRandomSeat(PcManager pcManager) {
        Random random = new Random();
        while (true) {
            int randomSeat = random.nextInt(1,17);
            if (pcManager.getUserIdFromPcList(randomSeat) == 0) {
                return randomSeat;
            }
        }
    }

}
