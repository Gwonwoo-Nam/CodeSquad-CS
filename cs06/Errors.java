public enum Errors {
    ILLEGAL_POSITION("[ERROR] 말의 위치가 적절하지 않습니다."),
    PIECE_EXIST("[ERROR] 이미 말이 존재합니다."),
    EXCEED_MAX("[ERROR] 지정된 말의 개수보다 많습니다."),
    NOT_EXIST_PIECE("[ERROR] 말이 없습니다."),
    NOT_YOUR_TURN("[ERROR] 현재 차례가 아닙니다."),
    OUT_OF_INDEX("[ERROR] index Out of Bounds");

    private final String message;
    Errors(String message) {
        this.message = message;
    }


    public void throwError() {
        throw new IllegalArgumentException(message);
    }

    public String get() {
        return message;
    }
}
