import java.util.Random;

public class RandomNumberGenerator {
    public static int chooseRandomSeat(ComputerDao computerDao) {
        Random random = new Random();
        while (true) {
            int randomSeat = random.nextInt(1,17);
            if (computerDao.getUserIdFromPcList(randomSeat) == 0) {
                return randomSeat;
            }
        }
    }
}
