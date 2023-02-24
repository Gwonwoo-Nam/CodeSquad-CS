import java.util.Random;

public class RandomNumberGenerator {
    public static int chooseRandomSeat(PcManager pcManager) {
        Random random = new Random();
        while (true) {
            int randomSeat = random.nextInt(1,17);
            if (pcManager.getUserIdFromPcList(randomSeat) == 0) {
                return randomSeat;
            }
        }
    }
}
