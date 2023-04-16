public enum Command {
    NEW("^new$") {
        @Override
        public void run(UserDao userDao, ComputerDao computerDao) {
            int randomSeat = RandomNumberGenerator.chooseRandomSeat(computerDao);
            User user = userDao.registerUser(randomSeat);
            computerDao.assignSeat(randomSeat, user.getUserId());
            System.out.println(randomSeat+"번 자리에 앉으세요 : #"+user.getUserId());
            computerDao.getEmptySeats();
        }

    }, STOP("^stop [0-9]*$") {
        @Override
        public void run(UserDao userDao, ComputerDao computerDao) {
            int userId = getValue();
            userDao.recordFinishTime(userId);
            int seatNumber = userDao.findUserSeatById(userId);
            computerDao.clearSeat(seatNumber);
            System.out.println("이제 "+seatNumber+"번 자리가 비었습니다.");
            computerDao.getEmptySeats();
        }

    };
    public abstract void run(UserDao userDao, ComputerDao computerDao);
    public String regex;
    private int value;



    Command(String regex) {
        this.regex = regex;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public boolean match(String command) {
        return command.matches(regex);
    }
}
