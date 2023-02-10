public enum CoffeeMenu {
    AMERICANO(1,3, "아메리카노"),
    CAFFE_LATTE(2,5, "카페라떼"),
    FRAPPUCCINO(3,10, "프라프치노");

    private final int MENU_NUMBER;
    private final int TIME;
    private final String NAME;

    CoffeeMenu(int MENU_NUMBER,int TIME, String NAME) {
        this.MENU_NUMBER = MENU_NUMBER;
        this.TIME = TIME;
        this.NAME = NAME;
    }

    public static CoffeeMenu getMenu(int order) {
        return switch (order) {
            case 1 -> CoffeeMenu.AMERICANO;
            case 2 -> CoffeeMenu.CAFFE_LATTE;
            case 3 -> CoffeeMenu.FRAPPUCCINO;
            default -> throw new IllegalArgumentException("[ERROR]");
        };

    }

    public String getName() {
        return NAME;
    }

    public int getMenuNumber() {
        return MENU_NUMBER;
    }

    public int getTIME() {
        return TIME;
    }
}
