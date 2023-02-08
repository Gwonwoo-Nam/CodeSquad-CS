public enum ControlMenu {
    POSSIBLE_LOCATION("^[?][A-H][1-8]$"),
    MOVE("^[A-H][1-8][\\-][\\>][A-H][1-8]$");

    private String regex;

    ControlMenu(String regex) {
        this.regex = regex;
    }

    public boolean choose(String command) {
        if (command.matches(regex)) {
            return true;
        }
        return false;
    }

}
