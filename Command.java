public enum Command {
    NEW("^new$") {
        @Override
        public void run(PcManager pcManager, PcController pcController) {
            int randomSeat = RandomNumberGenerator.chooseRandomSeat(pcManager);
            int userId = pcManager.registerUser(randomSeat);
            pcManager.assignSeat(randomSeat,userId);
            System.out.println(randomSeat+"번 자리에 앉으세요 : #"+userId);
            pcManager.printEmptySeat();
        }

    }, STOP("^stop [0-9]*$") {
        @Override
        public void run(PcManager pcManager, PcController pcController) {
            int userId = getValue();
            int seatNumber = pcManager.leaveUser(userId);
            System.out.println("이제 "+seatNumber+"번 자리가 비었습니다.");
            pcManager.printEmptySeat();
        }

    };
    public abstract void run(PcManager pcManager, PcController pcController);
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
